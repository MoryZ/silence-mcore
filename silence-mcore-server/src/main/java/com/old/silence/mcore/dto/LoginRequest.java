package com.old.silence.mcore.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * @author moryzang
 */
@Setter
@Getter
public class LoginRequest {

    @NotBlank
    private String code;
    private String rawData;
    private String signature;
    private String encryptedData;
    private String iv;
    private String phoneEncryptedData;
    private String phoneIv;
    private String phoneKey;
}
