package com.old.silence.mcore.vo;

import lombok.Data;

import java.util.List;

/**
 * 首页聚合视图（BFF 聚合接口）
 * <p>
 * 对应接口契约：GET /api/v1/poetryHome/summary
 * <p>
 * BFF 内部调用多个 content-service Feign 客户端拼装：
 * - activePlan：PoetryDailyStudyPlanFeignClient（现有接口，取第一条）
 * - dailyPoem：PoetryDailyPoemFeignClient（新增）
 * - statsOverview：PoetryStudyStatsFeignClient（新增）
 * - wrongQuestionCount：PoetryWrongQuestionsFeignClient（新增）
 * - newsNewCount：PoetryNewsFeignClient（新增）
 * <p>
 * 任一字段缺失前端用对应字段降级（不崩）。
 *
 * @author moryzang
 */
@Data
public class PoetryHomeSummaryMcoreView {

    /** 今日计划（接口1第一条，null 表示无计划） */
    private PoetryDailyStudyPlanMcoreView activePlan;

    /** 每日一诗（null 表示无） */
    private PoetryDailyPoemMcoreView dailyPoem;

    /** 学习概览 */
    private PoetryStudyStatsOverviewMcoreView statsOverview;

    /** 错题待复习数 */
    private Long wrongQuestionCount;

    /** 广场新内容数 */
    private Long newsNewCount;
}
