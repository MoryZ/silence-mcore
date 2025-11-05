package com.old.silence.mcore.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author moryzang
 */
@Getter
@Setter
public class VerifyCodeLoginRequest {
    private String code;
    private String phone;
    private String verifyCode;
}
