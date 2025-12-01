package com.old.silence.mcore.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.old.silence.json.JacksonMapper;
import com.old.silence.mcore.constant.SecurityConstants;
import com.old.silence.mcore.service.UserService;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final SilenceHallServerTokenAuthority jwtProvider;
    private final UserService userService;

    private final JacksonMapper jacksonMapper;

    public JwtFilter(SilenceHallServerTokenAuthority jwtProvider,
                      UserService userService,
                     JacksonMapper jacksonMapper) {
        this.jwtProvider = jwtProvider;
        this.userService = userService;
        this.jacksonMapper = jacksonMapper;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws
            ServletException, IOException {
        var tokenOptional = getToken(request);
        if (tokenOptional.isPresent()) {
            var errorCode = jwtProvider.verifyToken(tokenOptional.get());
            if (HttpStatus.UNAUTHORIZED.value() == errorCode) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            } else if (HttpStatus.FORBIDDEN.value() == errorCode) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return;
            } else if (HttpStatus.INTERNAL_SERVER_ERROR.value() == errorCode) {
                throw new RuntimeException("Internal server error while verifying JWT token");
            }
            var token = tokenOptional.get();
            String subject = jwtProvider.getSubject(token);
            if (jacksonMapper.validateJson(subject)) {
                var principal = jacksonMapper.fromJson(subject, SilencePrincipal.class);
                var userId = principal.getUserId();
                if (userService.existsByUserId(userId)) {
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            principal, null, null);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private Optional<String> getToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            return Optional.empty();
        }
        return Optional.of(authorizationHeader.replace(SecurityConstants.TOKEN_PREFIX, ""));

    }
}
