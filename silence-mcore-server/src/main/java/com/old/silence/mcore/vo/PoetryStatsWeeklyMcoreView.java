package com.old.silence.mcore.vo;

import lombok.Data;

/**
 * 本周学习统计视图（首页 + 统计页共用）
 * <p>
 * 对应接口契约：
 * - 首页 GET /api/v1/poetryStudyStats/overview 中的 weeklyProgress 字段
 * - 统计页 GET /api/v1/poetryStats/weekly
 * <p>
 * 周定义：周一 00:00:00 至当前
 *
 * @author moryzang
 */
@Data
public class PoetryStatsWeeklyMcoreView {

    /** 本周有学习的天数 */
    private Integer studyDays;

    /** 本周新学数量 */
    private Integer newPoems;

    /** 本周复习次数（每次复习算 1 次，同内容当天多次复习累加） */
    private Integer reviewCount;

    /** 本周学习时长（分钟） */
    private Integer studyMinutes;
}
