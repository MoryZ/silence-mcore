package com.old.silence.mcore.api;

import com.old.silence.mcore.client.content.WenYunDashboardFeignClient;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.security.SilenceHallContextHolder;
import com.old.silence.mcore.vo.WenYunDashboardTodayView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/v1")
public class WenYunDashboardResource {

    private final WenYunDashboardFeignClient dashboardFeignClient;

    public WenYunDashboardResource(WenYunDashboardFeignClient dashboardFeignClient) {
        this.dashboardFeignClient = dashboardFeignClient;
    }

    @GetMapping("/wenyun/dashboard/today")
    public ApiResult<WenYunDashboardTodayView> today() {
        BigInteger userId = SilenceHallContextHolder.getAuthenticatedUserId().orElse(BigInteger.ZERO);
        return ApiResult.success(dashboardFeignClient.today(userId));
    }
}
