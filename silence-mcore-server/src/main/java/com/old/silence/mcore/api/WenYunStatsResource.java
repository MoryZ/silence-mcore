package com.old.silence.mcore.api;

import com.old.silence.mcore.client.content.WenYunStatsFeignClient;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.security.SilenceHallContextHolder;
import com.old.silence.mcore.vo.WenYunAchievementView;
import com.old.silence.mcore.vo.WenYunStatsWeeklyView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/v1")
public class WenYunStatsResource {

    private final WenYunStatsFeignClient statsFeignClient;

    public WenYunStatsResource(WenYunStatsFeignClient statsFeignClient) {
        this.statsFeignClient = statsFeignClient;
    }

    @GetMapping("/wenyun/stats/weekly")
    public ApiResult<WenYunStatsWeeklyView> weekly() {
        BigInteger userId = SilenceHallContextHolder.getAuthenticatedUserId().orElse(BigInteger.ZERO);
        return ApiResult.success(statsFeignClient.weekly(userId));
    }

    @GetMapping("/wenyun/stats/achievement")
    public ApiResult<WenYunAchievementView> achievement() {
        BigInteger userId = SilenceHallContextHolder.getAuthenticatedUserId().orElse(BigInteger.ZERO);
        return ApiResult.success(statsFeignClient.achievement(userId));
    }
}
