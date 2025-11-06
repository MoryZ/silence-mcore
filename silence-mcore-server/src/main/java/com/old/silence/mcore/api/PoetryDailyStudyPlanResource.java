package com.old.silence.mcore.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.old.silence.core.context.CommonErrors;
import com.old.silence.mcore.client.content.PoetryDailyStudyPlanFeignClient;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.security.SilenceHallContextHolder;
import com.old.silence.mcore.vo.PoetryDailyStudyPlanMcoreView;

/**
 * @author moryzang
 */
@RestController
@RequestMapping("/api/v1")
public class PoetryDailyStudyPlanResource {

    private final PoetryDailyStudyPlanFeignClient poetryDailyStudyPlanFeignClient;

    public PoetryDailyStudyPlanResource(PoetryDailyStudyPlanFeignClient poetryDailyStudyPlanFeignClient) {
        this.poetryDailyStudyPlanFeignClient = poetryDailyStudyPlanFeignClient;
    }

    @GetMapping("/poetryDailyStudyPlan")
    public ApiResult<List<PoetryDailyStudyPlanMcoreView>> dailyPlans() {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElseThrow(CommonErrors.ACCESS_DENIED::createException);
        return ApiResult.success(poetryDailyStudyPlanFeignClient.findByUserIdAndPlanDate(userId, LocalDate.now(),
                PoetryDailyStudyPlanMcoreView.class));
    }

}
