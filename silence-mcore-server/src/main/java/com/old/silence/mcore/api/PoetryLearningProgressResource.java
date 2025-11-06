package com.old.silence.mcore.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.old.silence.core.context.CommonErrors;
import com.old.silence.core.exception.ResourceNotFoundException;
import com.old.silence.mcore.client.content.PoetryDailyStudyPlanFeignClient;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.security.SilenceHallContextHolder;
import com.old.silence.mcore.vo.PoetryDailyStudyPlanMcoreView;

import java.math.BigInteger;
import java.time.LocalDate;

/**
 * @author moryzang
 */
@RestController
@RequestMapping("/api/v1")
public class PoetryLearningProgressResource {

    private final PoetryDailyStudyPlanFeignClient poetryDailyStudyPlanFeignClient;

    public PoetryLearningProgressResource(PoetryDailyStudyPlanFeignClient poetryDailyStudyPlanFeignClient) {
        this.poetryDailyStudyPlanFeignClient = poetryDailyStudyPlanFeignClient;
    }

    @GetMapping("/poetryLearningProgress")
    public ApiResult<PoetryDailyStudyPlanMcoreView> progress(@RequestParam BigInteger subCategoryId) {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElseThrow(CommonErrors.ACCESS_DENIED::createException);
        return ApiResult.success(poetryDailyStudyPlanFeignClient.findByUserIdAndSubCategoryIdAndPlanDate(userId, subCategoryId, LocalDate.now(),
                PoetryDailyStudyPlanMcoreView.class).orElseThrow(ResourceNotFoundException::new));
    }
}
