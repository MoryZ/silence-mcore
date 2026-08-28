package com.old.silence.mcore.client.content;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.old.silence.mcore.vo.WenYunDashboardTodayView;

import java.math.BigInteger;

@FeignClient(name = "silence-content-service", contextId = "wenyun-dashboard", path = "/api/v1")
public interface WenYunDashboardFeignClient {

    @GetMapping("/wenyun/dashboard/today")
    WenYunDashboardTodayView today(@RequestParam BigInteger userId);
}
