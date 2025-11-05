package com.old.silence.mcore.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author moryzang
 */
@Getter
@Setter
public class SendVerifyCodeRequest {
    private String phone;
    private String captcha;
}
