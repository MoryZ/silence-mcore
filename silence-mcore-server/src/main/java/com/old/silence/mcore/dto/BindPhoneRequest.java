package com.old.silence.mcore.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author moryzang
 */
@Getter
@Setter
public class BindPhoneRequest {

    private String encryptedData;
    private String iv;
}
