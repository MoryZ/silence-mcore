package com.old.silence.mcore.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author moryzang
 */
@Setter
@Getter
public class UserResponse {

    private BigInteger id;
    private String nickname;
    private String avatar;
    private String phone;
    private String gender;
    private String birthday;
    private String address;

}
