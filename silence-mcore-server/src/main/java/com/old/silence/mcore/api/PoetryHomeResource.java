package com.old.silence.mcore.api;

import com.old.silence.mcore.client.content.PoetryDailyPoemFeignClient;
import com.old.silence.mcore.client.content.PoetryDailyStudyPlanFeignClient;
import com.old.silence.mcore.client.content.PoetryNewsFeignClient;
import com.old.silence.mcore.client.content.PoetryStudyStatsFeignClient;
import com.old.silence.mcore.client.content.PoetryWrongQuestionsFeignClient;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.security.SilenceHallContextHolder;
import com.old.silence.mcore.vo.NewsCountMcoreView;
import com.old.silence.mcore.vo.PoetryDailyPoemMcoreView;
import com.old.silence.mcore.vo.PoetryDailyStudyPlanMcoreView;
import com.old.silence.mcore.vo.PoetryHomeSummaryMcoreView;
import com.old.silence.mcore.vo.PoetryStudyStatsOverviewMcoreView;
import com.old.silence.mcore.vo.WrongQuestionCountMcoreView;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 首页资源控制器
 * <p>
 * 提供首页聚合接口和各子接口：
 * <ul>
 *   <li>GET /api/v1/poetryHome/summary — 聚合接口（推荐前端使用）</li>
 *   <li>GET /api/v1/poetryDailyPoem — 每日一诗</li>
 *   <li>GET /api/v1/poetryStudyStats/overview — 学习概览</li>
 *   <li>GET /api/v1/poetryWrongQuestions/count — 错题待复习数</li>
 *   <li>GET /api/v1/poetryNews/count — 广场新内容数</li>
 * </ul>
 * <p>
 * 游客（未登录）状态：
 * - 每日一诗正常返回（userId=0）
 * - 其他接口返回空值/0，前端隐藏对应模块
 *
 * @author moryzang
 */
@RestController
@RequestMapping("/api/v1")
public class PoetryHomeResource {

    private final PoetryDailyStudyPlanFeignClient poetryDailyStudyPlanFeignClient;
    private final PoetryDailyPoemFeignClient poetryDailyPoemFeignClient;
    private final PoetryStudyStatsFeignClient poetryStudyStatsFeignClient;
    private final PoetryWrongQuestionsFeignClient poetryWrongQuestionsFeignClient;
    private final PoetryNewsFeignClient poetryNewsFeignClient;

    public PoetryHomeResource(PoetryDailyStudyPlanFeignClient poetryDailyStudyPlanFeignClient,
                              PoetryDailyPoemFeignClient poetryDailyPoemFeignClient,
                              PoetryStudyStatsFeignClient poetryStudyStatsFeignClient,
                              PoetryWrongQuestionsFeignClient poetryWrongQuestionsFeignClient,
                              PoetryNewsFeignClient poetryNewsFeignClient) {
        this.poetryDailyStudyPlanFeignClient = poetryDailyStudyPlanFeignClient;
        this.poetryDailyPoemFeignClient = poetryDailyPoemFeignClient;
        this.poetryStudyStatsFeignClient = poetryStudyStatsFeignClient;
        this.poetryWrongQuestionsFeignClient = poetryWrongQuestionsFeignClient;
        this.poetryNewsFeignClient = poetryNewsFeignClient;
    }

    /**
     * 首页聚合接口（推荐前端使用，一次拿全首页数据）
     * <p>
     * 内部调用 5 个 Feign 客户端拼装，任一字段缺失前端用对应字段降级（不崩）。
     */
    @GetMapping("/poetryHome/summary")
    public ApiResult<PoetryHomeSummaryMcoreView> summary() {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElse(BigInteger.ZERO);
        var summary = new PoetryHomeSummaryMcoreView();

        // 1. 今日计划（现有接口，取第一条）
        try {
            var plans = poetryDailyStudyPlanFeignClient.findByUserIdAndPlanDate(
                    userId, LocalDate.now(), PoetryDailyStudyPlanMcoreView.class);
            summary.setActivePlan(plans.isEmpty() ? null : plans.get(0));
        } catch (Exception e) {
            summary.setActivePlan(null);
        }

        // 2. 每日一诗
        try {
            summary.setDailyPoem(poetryDailyPoemFeignClient.getDailyPoem(userId));
        } catch (Exception e) {
            summary.setDailyPoem(null);
        }

        // 3. 学习概览
        try {
            summary.setStatsOverview(poetryStudyStatsFeignClient.getOverview(userId));
        } catch (Exception e) {
            summary.setStatsOverview(new PoetryStudyStatsOverviewMcoreView());
        }

        // 4. 错题待复习数
        try {
            summary.setWrongQuestionCount(poetryWrongQuestionsFeignClient.countPending(userId));
        } catch (Exception e) {
            summary.setWrongQuestionCount(0L);
        }

        // 5. 广场新内容数
        try {
            summary.setNewsNewCount(poetryNewsFeignClient.countNew(null));
        } catch (Exception e) {
            summary.setNewsNewCount(0L);
        }

        return ApiResult.success(summary);
    }

    /**
     * 每日一诗
     */
    @GetMapping("/poetryDailyPoem")
    public ApiResult<PoetryDailyPoemMcoreView> dailyPoem() {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElse(BigInteger.ZERO);
        return ApiResult.success(poetryDailyPoemFeignClient.getDailyPoem(userId));
    }

    /**
     * 学习概览（首页 Tier3 激励）
     */
    @GetMapping("/poetryStudyStats/overview")
    public ApiResult<PoetryStudyStatsOverviewMcoreView> studyStatsOverview() {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElse(BigInteger.ZERO);
        return ApiResult.success(poetryStudyStatsFeignClient.getOverview(userId));
    }

    /**
     * 错题待复习数
     */
    @GetMapping("/poetryWrongQuestions/count")
    public ApiResult<WrongQuestionCountMcoreView> wrongQuestionCount() {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElse(BigInteger.ZERO);
        var count = poetryWrongQuestionsFeignClient.countPending(userId);
        return ApiResult.success(new WrongQuestionCountMcoreView(count));
    }

    /**
     * 广场新内容数
     *
     * @param since 可选，ISO 8601 时间，不传则返回总未读数
     */
    @GetMapping("/poetryNews/count")
    public ApiResult<NewsCountMcoreView> newsCount(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime since) {
        var count = poetryNewsFeignClient.countNew(since);
        return ApiResult.success(new NewsCountMcoreView(count));
    }
}
