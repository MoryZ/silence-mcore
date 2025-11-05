package com.old.silence.mcore.security;

import java.math.BigInteger;

public class SilenceHallUser {

    private BigInteger userId;
    private String unionid;
    private String nickname;

    public SilenceHallUser() {
    }

    public SilenceHallUser(BigInteger userId, String unionid, String nickname) {
        this.userId = userId;
        this.unionid = unionid;
        this.nickname = nickname;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
