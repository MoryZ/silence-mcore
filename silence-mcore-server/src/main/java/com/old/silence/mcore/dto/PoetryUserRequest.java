package com.old.silence.mcore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import com.old.silence.content.domain.enums.GradeLevel;

/**
 * @author moryzang
 */
@Data
public class PoetryUserRequest {

    @NotBlank
    @Size(max = 64)
    private String openid;
    private String unionid;
    private String nickname;
    private String avatarUrl;
    private String phone;
    @Size(max = 16)
    private String gender;
    @Size(max = 32)
    private String birthday;
    @Size(max = 255)
    private String address;
    private GradeLevel gradeLevel;
    private Long studyGoalDaily;
}
