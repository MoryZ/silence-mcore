package com.old.silence.mcore.security;

import java.math.BigInteger;


public class SilencePrincipal {
    private BigInteger userId;
    private String unionId;
    private String nickName;

    public SilencePrincipal() {
    }


    public SilencePrincipal(BigInteger userId, String unionId) {
        this.userId = userId;
        this.unionId = unionId;
    }

    public SilencePrincipal(BigInteger userId, String unionId, String nickName) {
        this.userId = userId;
        this.unionId = unionId;
        this.nickName = nickName;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
