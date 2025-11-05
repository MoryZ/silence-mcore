package com.old.silence.mcore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.Instant;


/**
 * @author moryzang
 */
@Getter
@Setter
public class PoetryUserLoginLogRequest {

    @NotNull
    private BigInteger userId;
    @NotBlank
    @Size(max = 64)
    private String openid;
    @NotNull
    private Byte loginType;
    private String ipAddress;
    private String sessionKey;
    @NotNull
    private Boolean loginStatus;
    @NotNull
    private Instant loginTime;
}
