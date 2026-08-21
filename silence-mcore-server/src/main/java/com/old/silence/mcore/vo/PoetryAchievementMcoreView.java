package com.old.silence.mcore.vo;

import lombok.Data;

/**
 * 成就徽章视图（统计页）
 * <p>
 * 对应接口契约：GET /api/v1/poetryStats/achievements
 * <p>
 * 业务规则：
 * - 后端预定义成就规则（连续打卡/学习总数/笔记总数/收藏数等）
 * - unlocked=true 不可再变化，unlocked=false 可带 progress 展示进度
 * - 当前前端展示 4 个示例，可固定这 4 个成就规则
 *
 * @author moryzang
 */
@Data
public class PoetryAchievementMcoreView {

    /** emoji 或图片URL（后续可换 iconfont） */
    private String icon;

    /** 徽章名 */
    private String title;

    /** 解锁条件描述 */
    private String desc;

    /** 是否已解锁 */
    private Boolean unlocked;

    /** 解锁日期 YYYY-MM-DD，仅 unlocked=true 时返回 */
    private String unlockedAt;

    /** 解锁进度，仅 unlocked=false 时返回 */
    private PoetryAchievementProgressMcoreView progress;
}
