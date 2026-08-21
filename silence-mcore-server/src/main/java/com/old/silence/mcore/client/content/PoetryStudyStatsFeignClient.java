package com.old.silence.mcore.client.content;

import com.old.silence.mcore.vo.PoetryStudyStatsOverviewMcoreView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

/**
 * 学习概览 Feign 客户端（首页 Tier3 激励）
 * <p>
 * content-service 需实现端点：GET /api/v1/poetryStudyStats/overview
 * <p>
 * 业务规则：
 * - streakDays：连续有学习记录的天数，中断归零重计
 * - 打卡定义：当日完成至少 1 项学习内容（新学或复习）
 * - 游客（userId=0）返回 streakDays=0，进度条隐藏
 *
 * @author moryzang
 */
@FeignClient(name = "silence-content-service", contextId = "poetryStudyStats", path = "/api/v1")
public interface PoetryStudyStatsFeignClient {

    /**
     * 获取学习概览
     *
     * @param userId 用户ID，游客传 BigInteger.ZERO
     * @return 学习概览视图（streakDays, totalStudyDays, weeklyProgress, categoryProgress, todayCompleted）
     */
    @GetMapping("/poetryStudyStats/overview")
    PoetryStudyStatsOverviewMcoreView getOverview(@RequestParam BigInteger userId);
}
