package com.old.silence.mcore.api;

import jakarta.validation.Valid;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.old.silence.core.context.CommonErrors;
import com.old.silence.core.exception.ResourceNotFoundException;
import com.old.silence.mcore.client.content.PoetryUserFeignClient;
import com.old.silence.mcore.dto.PoetryUserRequest;
import com.old.silence.mcore.dto.UpdateUserProfileRequest;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.security.SilenceHallContextHolder;
import com.old.silence.mcore.vo.PoetryUserMCoreView;
import com.old.silence.mcore.vo.UserResponse;

import java.math.BigInteger;

/**
 * @author moryzang
 */
@RestController
@RequestMapping("/api/v1")
@Validated
public class UserProfileResource {

    private static final String GENDER_MALE = "MALE";
    private static final String GENDER_FEMALE = "FEMALE";

    private final PoetryUserFeignClient poetryUserFeignClient;

    public UserProfileResource(PoetryUserFeignClient poetryUserFeignClient) {
        this.poetryUserFeignClient = poetryUserFeignClient;
    }

    @GetMapping("/users/me")
    public ApiResult<UserResponse> me() {
        var user = getCurrentUser();
        return ApiResult.success(buildUserResponse(user));
    }

    @PutMapping("/users/me/profile")
    public ApiResult<UserResponse> updateProfile(@Valid @RequestBody UpdateUserProfileRequest request) {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElseThrow(CommonErrors.ACCESS_DENIED::createException);
        PoetryUserMCoreView currentUser = getCurrentUser();

        PoetryUserRequest updateRequest = new PoetryUserRequest();
        updateRequest.setOpenid(currentUser.getOpenid());
        updateRequest.setPhone(currentUser.getPhone());
        updateRequest.setGradeLevel(currentUser.getGradeLevel());
        updateRequest.setStudyGoalDaily(currentUser.getStudyGoalDaily());
        updateRequest.setNickname(request.getNickname());
        updateRequest.setAvatarUrl(request.getAvatarUrl());
        updateRequest.setGender(request.getGender());
        updateRequest.setBirthday(request.getBirthday());
        updateRequest.setAddress(request.getAddress());

        poetryUserFeignClient.update(userId, updateRequest);

        PoetryUserMCoreView updatedUser = poetryUserFeignClient.findById(userId, PoetryUserMCoreView.class)
                .orElseThrow(ResourceNotFoundException::new);
        return ApiResult.success(buildUserResponse(updatedUser));
    }

    private PoetryUserMCoreView getCurrentUser() {
        BigInteger userId = SilenceHallContextHolder.getAuthenticatedUserId().orElseThrow(CommonErrors.ACCESS_DENIED::createException);
        return poetryUserFeignClient.findById(userId, PoetryUserMCoreView.class).orElseThrow(ResourceNotFoundException::new);
    }

    private UserResponse buildUserResponse(PoetryUserMCoreView user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setAvatar(user.getAvatarUrl());
        userResponse.setNickname(user.getNickname());
        userResponse.setPhone(user.getPhone());
        userResponse.setGender(user.getGender());
        userResponse.setBirthday(user.getBirthday());
        userResponse.setAddress(user.getAddress());
        return userResponse;
    }


}
