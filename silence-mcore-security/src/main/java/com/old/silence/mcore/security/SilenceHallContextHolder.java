package com.old.silence.mcore.security;

import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigInteger;
import java.util.Optional;

public class SilenceHallContextHolder {

    public static Optional<BigInteger> getAuthenticatedUserId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            var principal = authentication.getPrincipal();
            if (principal instanceof SilencePrincipal) {
                var userId = ((SilencePrincipal) principal).getUserId();
                return Optional.of(userId);
            }
        }
        return Optional.empty();
    }

    public static Optional<SilenceHallUser> getAuthenticatedUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            var principal = authentication.getPrincipal();
            if (principal instanceof SilencePrincipal) {
                var userId = ((SilencePrincipal) principal).getUserId();
                var unionId = ((SilencePrincipal) principal).getUnionId();
                var nickname = ((SilencePrincipal) principal).getNickName();
                return Optional.of(new SilenceHallUser(userId, unionId, nickname));
            }
        }
        return Optional.empty();
    }

}
