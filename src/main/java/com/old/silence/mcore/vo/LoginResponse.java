package com.old.silence.mcore.vo;

/**
 * @author moryzang
 */

public class LoginResponse {
    private String token;
    private UserResponse userInfo;
    private String openid;
    private String sessionKey; // 注意：sessionKey不应该返回给前端，这里只是示例

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserResponse getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserResponse userInfo) {
        this.userInfo = userInfo;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
