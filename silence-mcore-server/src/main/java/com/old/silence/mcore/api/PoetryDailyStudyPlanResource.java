package com.old.silence.mcore.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.old.silence.mcore.client.content.PoetryDailyStudyPlanFeignClient;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.security.SilenceHallContextHolder;
import com.old.silence.mcore.vo.PoetryDailyStudyPlanMcoreView;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

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
        var userId = SilenceHallContextHolder.getAuthenticatedUserId()
                .orElse(BigInteger.ZERO);
        return ApiResult.success(poetryDailyStudyPlanFeignClient.findByUserIdAndPlanDate(userId, LocalDate.now(),
                PoetryDailyStudyPlanMcoreView.class));
    }

}
