package com.old.silence.mcore.vo;

import lombok.Data;

/**
 * 分类进度视图（首页 + 统计页共用）
 * <p>
 * 对应接口契约：
 * - 首页 GET /api/v1/poetryStudyStats/overview 中的 categoryProgress 数组项
 * - 统计页 GET /api/v1/poetryStats/categoryProgress 数组项
 * <p>
 * 按用户已选学习计划关联的分类生成，无学习计划时返回空数组。
 * 排序：按 learned/total 降序，或按分类ID。
 *
 * @author moryzang
 */
@Data
public class PoetryCategoryProgressMcoreView {

    /** 分类名（如"唐诗"） */
    private String name;

    /** 该分类总诗词数 */
    private Integer total;

    /** 已学数 */
    private Integer learned;

    /** 完成百分比（0-100，整数） */
    private Integer percent;
}
