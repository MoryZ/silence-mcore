package com.old.silence.mcore.vo;

import lombok.Data;

/**
 * 最近学习记录视图（统计页）
 * <p>
 * 对应接口契约：GET /api/v1/poetryStats/recentStudy
 * <p>
 * 业务规则：
 * - 按学习完成时间倒序
 * - 后端预格式化 date 为"今天 HH:mm"/"昨天 HH:mm"/"MM-dd HH:mm"
 * - type 限定两个值："新学" / "复习"
 *
 * @author moryzang
 */
@Data
public class PoetryRecentStudyMcoreView {

    /** 诗词标题 */
    private String title;

    /** 作者 */
    private String author;

    /** 友好时间展示（前端展示用，后端预格式化） */
    private String date;

    /** "新学" / "复习"（前端用此做不同颜色标签） */
    private String type;
}
