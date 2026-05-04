package com.old.silence.mcore.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.old.silence.json.JacksonMapper;
import com.old.silence.mcore.constant.SecurityConstants;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Component
public class SilenceHallServerTokenAuthority implements SilenceHallTokenAuthority {

    private static final Logger LOGGER = LoggerFactory.getLogger(SilenceHallServerTokenAuthority.class);
    private final JacksonMapper jacksonMapper;
    @Value("${silence.hall.jwt.secret:silence-hall}")
    private String jwtSecret;
    @Value("${silence.hall.jwt.expiration:30}")
    private Long jwtExpirationSeconds;

    public SilenceHallServerTokenAuthority(JacksonMapper jacksonMapper) {
        this.jacksonMapper = jacksonMapper;
    }


    @Override
    public String issueToken(SilencePrincipal principal) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("alg", algorithm.getName());
        headerClaims.put("typ", SecurityConstants.TOKEN_TYPE);

        Instant now = Instant.now();
        return JWT.create()
                .withHeader(headerClaims)
                .withSubject(jacksonMapper.toJson(principal))
                .withIssuer(SecurityConstants.TOKEN_ISSUER)
                .withAudience(SecurityConstants.TOKEN_AUDIENCE)
                .withIssuedAt(now)
                .withExpiresAt(now.plus(jwtExpirationSeconds, ChronoUnit.DAYS))
                .sign(algorithm);
    }

    @Override
    public int verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            verifier.verify(token);
        } catch (JWTDecodeException | SignatureVerificationException ex) {
            LOGGER.error("verify token failed:{}", ex.getLocalizedMessage());
            return HttpStatus.UNAUTHORIZED.value(); // 401 Unauthorized
        } catch (TokenExpiredException ex) {
            LOGGER.warn("The token is expired:{}", token);
            return HttpStatus.FORBIDDEN.value(); // 403 Forbidden
        }
        return HttpStatus.OK.value(); // 200 OK
    }

    @Override
    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256(jwtSecret))
                .build().verify(token)
                .getSubject();
    }
}
