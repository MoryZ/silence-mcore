package com.old.silence.mcore.vo;

import lombok.Data;

import java.util.List;

/**
 * 统计页聚合视图（BFF 聚合接口）
 * <p>
 * 对应接口契约：GET /api/v1/poetryStats/summary
 * <p>
 * BFF 内部调用 PoetryStatsFeignClient 的 5 个方法拼装：
 * - overview：学习总览
 * - weekly：本周学习
 * - categoryProgress：各分类进度
 * - recentStudy：最近学习记录
 * - achievements：成就徽章
 * <p>
 * 任一字段缺失前端用 || [] / || {} 兜底，不崩。
 *
 * @author moryzang
 */
@Data
public class PoetryStatsSummaryMcoreView {

    /** 学习总览 */
    private PoetryStatsOverviewMcoreView overview;

    /** 本周学习 */
    private PoetryStatsWeeklyMcoreView weekly;

    /** 各分类进度 */
    private List<PoetryCategoryProgressMcoreVo> categoryProgress;

    /** 最近学习记录列表 */
    private List<PoetryRecentStudyMcoreView> recentStudy;

    /** 成就徽章列表 */
    private List<PoetryAchievementMcoreView> achievements;
}
