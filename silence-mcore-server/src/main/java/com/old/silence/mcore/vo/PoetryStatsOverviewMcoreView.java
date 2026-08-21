package com.old.silence.mcore.vo;

import lombok.Data;

/**
 * 学习总览视图（统计页）
 * <p>
 * 对应接口契约：GET /api/v1/poetryStats/overview
 * <p>
 * 业务规则：
 * - totalStudyTime：所有已完成的学习会话时长累计
 * - studyDays：有过学习记录的不重复天数
 * - learnedPoems：状态=已完成的诗词ID 去重计数
 * - totalNotes：用户所有笔记总数
 *
 * @author moryzang
 */
@Data
public class PoetryStatsOverviewMcoreView {

    /** 累计学习时长（分钟） */
    private Integer totalStudyTime;

    /** 累计学习天数 */
    private Integer studyDays;

    /** 已学诗词数量（去重） */
    private Integer learnedPoems;

    /** 累计笔记数量 */
    private Integer totalNotes;
}
