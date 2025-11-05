package com.old.silence.mcore.security;

public interface SilenceHallTokenAuthority {

    default String issueToken(SilencePrincipal principal){
        return null;
    }

    boolean verifyToken(String token);

    String getSubject(String token);
}
