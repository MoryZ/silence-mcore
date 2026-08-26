package com.old.silence.mcore.vo;

import lombok.Data;

import java.util.List;

/**
 * 学习概览视图（首页 Tier3 激励）
 * <p>
 * 对应接口契约：GET /api/v1/poetryStudyStats/overview
 * <p>
 * 业务规则：
 * - streakDays：连续有学习记录的天数，中断归零重计
 * - 打卡定义：当日完成至少 1 项学习内容（新学或复习）
 * - 首页取 categoryProgress 第一条展示进度条
 * - todayCompleted 控制首页 Tier1 横幅文案
 * <p>
 * 降级策略：失败时 streakDays=0，进度条隐藏，不阻断首页。
 *
 * @author moryzang
 */
@Data
public class PoetryStudyStatsOverviewMcoreVo {

    /** 连续打卡天数 */
    private Integer streakDays;

    /** 累计学习天数 */
    private Integer totalStudyDays;

    /** 本周学习数据 */
    private PoetryStatsWeeklyMcoreView weeklyProgress;

    /** 各分类进度（首页取第一条展示进度条） */
    private List<PoetryCategoryProgressMcoreVo> categoryProgress;

    /** 今日是否已完成（控制 Tier1 横幅文案） */
    private Boolean todayCompleted;
}
