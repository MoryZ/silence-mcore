package com.old.silence.mcore.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author moryzang
 */

@Setter
@Getter
public class LoginVo {
    private String token;
    private UserResponse userInfo;
    private String openid;

}
