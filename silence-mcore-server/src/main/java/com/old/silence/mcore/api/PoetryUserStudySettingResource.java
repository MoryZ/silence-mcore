package com.old.silence.mcore.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.old.silence.core.context.CommonErrors;
import com.old.silence.mcore.client.content.PoetryUserStudySettingFeignClient;
import com.old.silence.mcore.dto.PoetryUserStudySettingRequest;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.security.SilenceHallContextHolder;
import com.old.silence.mcore.vo.PoetryUserStudySettingMcoreView;

import java.math.BigInteger;

/**
 * @author moryzang
 */
@RestController
@RequestMapping("/api/v1")
public class PoetryUserStudySettingResource {

    private final PoetryUserStudySettingFeignClient poetryUserStudySettingFeignClient;

    public PoetryUserStudySettingResource(PoetryUserStudySettingFeignClient poetryUserStudySettingFeignClient) {
        this.poetryUserStudySettingFeignClient = poetryUserStudySettingFeignClient;
    }

    @GetMapping("/poetryUserStudySettings/{subCategoryId}/{gradeId}")
    public ApiResult<PoetryUserStudySettingMcoreView> findBySubCategoryIdGradeIdAndUserId(@PathVariable BigInteger subCategoryId, @PathVariable BigInteger gradeId) {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElseThrow(CommonErrors.ACCESS_DENIED::createException);
        return ApiResult.success(poetryUserStudySettingFeignClient.findBySubCategoryIdGradeIdAndUserId(subCategoryId, gradeId, userId, PoetryUserStudySettingMcoreView.class)
                .orElse(null));
    }

    @PostMapping(value = "/poetryUserStudySettings")
    public ApiResult<String> create(@RequestBody PoetryUserStudySettingRequest poetryUserStudySettingCommand) {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElseThrow(CommonErrors.ACCESS_DENIED::createException);
        poetryUserStudySettingCommand.setUserId(userId);
        return ApiResult.success(String.valueOf(poetryUserStudySettingFeignClient.create(poetryUserStudySettingCommand)));
    }

    @PutMapping("/poetryUserStudySettings/{id}")
    public ApiResult<Boolean> update(@PathVariable BigInteger id, @RequestBody PoetryUserStudySettingRequest poetryUserStudySettingCommand) {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElseThrow(CommonErrors.ACCESS_DENIED::createException);
        poetryUserStudySettingCommand.setUserId(userId);
        poetryUserStudySettingFeignClient.update(id, poetryUserStudySettingCommand);
        return ApiResult.success(true);
    }
}
