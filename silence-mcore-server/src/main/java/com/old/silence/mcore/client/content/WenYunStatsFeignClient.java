package com.old.silence.mcore.client.content;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.old.silence.mcore.vo.WenYunAchievementView;
import com.old.silence.mcore.vo.WenYunStatsWeeklyView;

import java.math.BigInteger;

@FeignClient(name = "silence-content-service", contextId = "wenyun-stats", path = "/api/v1")
public interface WenYunStatsFeignClient {

    @GetMapping("/wenyun/stats/weekly")
    WenYunStatsWeeklyView weekly(@RequestParam BigInteger userId);

    @GetMapping("/wenyun/stats/achievement")
    WenYunAchievementView achievement(@RequestParam BigInteger userId);
}
