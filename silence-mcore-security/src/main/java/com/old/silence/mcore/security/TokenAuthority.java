package com.old.silence.mcore.security;

/*public class TokenAuthority implements SilenceHallTokenAuthority{
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenAuthority.class);

    @Override
    public boolean verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.TOKEN_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            verifier.verify(token);
        }catch (JWTDecodeException | SignatureVerificationException e){
            LOGGER.error(e.getLocalizedMessage());
            return false;
        }catch (TokenExpiredException ex){
            LOGGER.warn(ex.getLocalizedMessage());
            return false;
        }
        return true;
    }

    @Override
    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256(SecurityConstants.TOKEN_SECRET))
                .build()
                .verify(token)
                .getSubject();
    }
}*/
