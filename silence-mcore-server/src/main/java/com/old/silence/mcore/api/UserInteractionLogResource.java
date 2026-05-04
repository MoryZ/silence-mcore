package com.old.silence.mcore.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.old.silence.core.context.CommonErrors;
import com.old.silence.mcore.client.content.UserInteractionLogFeignClient;
import com.old.silence.mcore.dto.UserInteractionLogMcoreQuery;
import com.old.silence.mcore.dto.UserInteractionLogRequest;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.security.SilenceHallContextHolder;
import com.old.silence.mcore.vo.PoetryUserFavoriteMcoreView;

import java.math.BigInteger;


/**
 * UserInteractionLog资源控制器
 */
@RestController
@RequestMapping("/api/v1")
public class UserInteractionLogResource {
    private final UserInteractionLogFeignClient userInteractionLogFeignClient;

    public UserInteractionLogResource(UserInteractionLogFeignClient userInteractionLogFeignClient) {
        this.userInteractionLogFeignClient = userInteractionLogFeignClient;
    }

    @GetMapping("/userInteractionLog")
    public ApiResult<Page<PoetryUserFavoriteMcoreView>> query(UserInteractionLogMcoreQuery query, Pageable pageable) {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElseThrow(CommonErrors.ACCESS_DENIED::createException);
        query.setUserId(userId);
        return ApiResult.success(userInteractionLogFeignClient.query(query, pageable, PoetryUserFavoriteMcoreView.class));
    }


    @PostMapping(value = "/userInteractionLog")
    public ApiResult<String> create(@RequestBody UserInteractionLogRequest userInteractionLogRequest) {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId()
                .orElseThrow(CommonErrors.ACCESS_DENIED::createException);
        userInteractionLogRequest.setUserId(userId);
        return ApiResult.success(String.valueOf(userInteractionLogFeignClient.create(userInteractionLogRequest)));
    }


    @DeleteMapping("/userInteractionLog/{id}")
    public ApiResult<Boolean> deleteById(@PathVariable BigInteger id) {
        userInteractionLogFeignClient.deleteById(id);
        return ApiResult.success(true);
    }
}