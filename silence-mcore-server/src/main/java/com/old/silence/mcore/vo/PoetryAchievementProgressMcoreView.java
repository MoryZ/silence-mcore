package com.old.silence.mcore.vo;

import lombok.Data;

/**
 * 成就解锁进度视图
 * <p>
 * 仅在 unlocked=false 时返回，展示当前进度。
 *
 * @author moryzang
 */
@Data
public class PoetryAchievementProgressMcoreView {

    /** 当前进度 */
    private Integer current;

    /** 目标值 */
    private Integer target;
}
