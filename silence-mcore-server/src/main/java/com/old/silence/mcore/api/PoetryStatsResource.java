package com.old.silence.mcore.api;

import com.old.silence.mcore.client.content.PoetryStatsFeignClient;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.security.SilenceHallContextHolder;
import com.old.silence.mcore.vo.PoetryAchievementMcoreView;
import com.old.silence.mcore.vo.PoetryCategoryProgressMcoreView;
import com.old.silence.mcore.vo.PoetryRecentStudyMcoreView;
import com.old.silence.mcore.vo.PoetryStatsOverviewMcoreView;
import com.old.silence.mcore.vo.PoetryStatsSummaryMcoreView;
import com.old.silence.mcore.vo.PoetryStatsWeeklyMcoreView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

/**
 * 统计页资源控制器
 * <p>
 * 提供统计页聚合接口和各子接口：
 * <ul>
 *   <li>GET /api/v1/poetryStats/summary — 聚合接口（推荐前端使用）</li>
 *   <li>GET /api/v1/poetryStats/overview — 学习总览</li>
 *   <li>GET /api/v1/poetryStats/weekly — 本周学习</li>
 *   <li>GET /api/v1/poetryStats/categoryProgress — 分类进度</li>
 *   <li>GET /api/v1/poetryStats/recentStudy — 最近学习记录</li>
 *   <li>GET /api/v1/poetryStats/achievements — 成就徽章</li>
 * </ul>
 * <p>
 * 游客（未登录）状态：所有接口返回空值/0，不抛 401，前端展示空状态。
 *
 * @author moryzang
 */
@RestController
@RequestMapping("/api/v1")
public class PoetryStatsResource {

    private final PoetryStatsFeignClient poetryStatsFeignClient;

    public PoetryStatsResource(PoetryStatsFeignClient poetryStatsFeignClient) {
        this.poetryStatsFeignClient = poetryStatsFeignClient;
    }

    /**
     * 统计页聚合接口（推荐前端使用，一次拿全 5 项数据）
     * <p>
     * 内部调用 5 个 Feign 方法拼装，任一字段缺失用 || [] / || {} 兜底，不崩。
     */
    @GetMapping("/poetryStats/summary")
    public ApiResult<PoetryStatsSummaryMcoreView> summary() {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElse(BigInteger.ZERO);
        var summary = new PoetryStatsSummaryMcoreView();

        // 1. 学习总览
        try {
            summary.setOverview(poetryStatsFeignClient.getOverview(userId));
        } catch (Exception e) {
            summary.setOverview(new PoetryStatsOverviewMcoreView());
        }

        // 2. 本周学习
        try {
            summary.setWeekly(poetryStatsFeignClient.getWeekly(userId));
        } catch (Exception e) {
            summary.setWeekly(new PoetryStatsWeeklyMcoreView());
        }

        // 3. 分类进度
        try {
            summary.setCategoryProgress(poetryStatsFeignClient.getCategoryProgress(userId));
        } catch (Exception e) {
            summary.setCategoryProgress(List.of());
        }

        // 4. 最近学习记录
        try {
            summary.setRecentStudy(poetryStatsFeignClient.getRecentStudy(userId, 5));
        } catch (Exception e) {
            summary.setRecentStudy(List.of());
        }

        // 5. 成就徽章
        try {
            summary.setAchievements(poetryStatsFeignClient.getAchievements(userId));
        } catch (Exception e) {
            summary.setAchievements(List.of());
        }

        return ApiResult.success(summary);
    }

    /**
     * 学习总览：累计学习时长/天数/已学诗词/笔记数
     */
    @GetMapping("/poetryStats/overview")
    public ApiResult<PoetryStatsOverviewMcoreView> overview() {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElse(BigInteger.ZERO);
        return ApiResult.success(poetryStatsFeignClient.getOverview(userId));
    }

    /**
     * 本周学习：天数/新学/复习/时长
     */
    @GetMapping("/poetryStats/weekly")
    public ApiResult<PoetryStatsWeeklyMcoreView> weekly() {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElse(BigInteger.ZERO);
        return ApiResult.success(poetryStatsFeignClient.getWeekly(userId));
    }

    /**
     * 各分类进度（已学/总数/百分比）
     */
    @GetMapping("/poetryStats/categoryProgress")
    public ApiResult<List<PoetryCategoryProgressMcoreView>> categoryProgress() {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElse(BigInteger.ZERO);
        return ApiResult.success(poetryStatsFeignClient.getCategoryProgress(userId));
    }

    /**
     * 最近学习记录列表
     *
     * @param limit 可选，默认 5，最大 20
     */
    @GetMapping("/poetryStats/recentStudy")
    public ApiResult<List<PoetryRecentStudyMcoreView>> recentStudy(
            @RequestParam(defaultValue = "5") Integer limit) {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElse(BigInteger.ZERO);
        var effectiveLimit = Math.min(Math.max(limit, 1), 20);
        return ApiResult.success(poetryStatsFeignClient.getRecentStudy(userId, effectiveLimit));
    }

    /**
     * 成就徽章列表（解锁状态）
     */
    @GetMapping("/poetryStats/achievements")
    public ApiResult<List<PoetryAchievementMcoreView>> achievements() {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElse(BigInteger.ZERO);
        return ApiResult.success(poetryStatsFeignClient.getAchievements(userId));
    }
}
