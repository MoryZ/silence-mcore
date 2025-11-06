package com.old.silence.mcore.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author moryzang
 */
@Setter
@Getter
public class WxMaLoginResult {

    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 会话密钥
     */
    private String sessionKey;

    /**
     * 用户在开放平台的唯一标识符
     */
    private String unionid;

    /**
     * 过期时间（时间戳）
     */
    private Long expiresTime;

}
