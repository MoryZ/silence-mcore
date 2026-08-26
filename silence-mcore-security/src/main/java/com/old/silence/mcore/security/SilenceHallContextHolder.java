package com.old.silence.mcore.security;

import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigInteger;
import java.util.Optional;

public class SilenceHallContextHolder {

    public static Optional<BigInteger> getAuthenticatedUserId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            var principal = authentication.getPrincipal();
            if (principal instanceof SilencePrincipal silencePrincipal) {
                var userId = silencePrincipal.getUserId();
                return Optional.of(userId);
            }
        }
        return Optional.empty();
    }

    public static Optional<SilenceHallUser> getAuthenticatedUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            var principal = authentication.getPrincipal();
            if (principal instanceof SilencePrincipal silencePrincipal) {
                var userId = silencePrincipal.getUserId();
                var unionId = silencePrincipal.getUnionId();
                var nickname = silencePrincipal.getNickName();
                return Optional.of(new SilenceHallUser(userId, unionId, nickname));
            }
        }
        return Optional.empty();
    }

}
