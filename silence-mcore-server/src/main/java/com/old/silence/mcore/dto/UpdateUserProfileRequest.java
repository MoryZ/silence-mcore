package com.old.silence.mcore.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * @author moryzang
 */
@Getter
@Setter
public class UpdateUserProfileRequest {

    @Size(max = 32)
    private String nickname;

    @Size(max = 512)
    private String avatarUrl;

    @Size(max = 16)
    private String gender;

    @Size(max = 32)
    private String birthday;

    @Size(max = 255)
    private String address;
}
