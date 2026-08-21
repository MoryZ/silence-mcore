package com.old.silence.mcore.vo;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;

/**
 * 每日一诗视图（首页内容发现层）
 * <p>
 * 对应接口契约：GET /api/v1/poetryDailyPoem
 * content-service 需实现该端点，按日期+用户随机选取一首诗词，当日固定不变。
 *
 * @author moryzang
 */
@Data
public class PoetryDailyPoemMcoreView {

    /** 诗词ID，点击可跳详情 */
    private BigInteger id;

    /** 标题 */
    private String title;

    /** 作者 */
    private String author;

    /** 朝代 */
    private String dynasty;

    /** 原文，\n 分句 */
    private String content;

    /** 译文（卡片折叠时可选展开） */
    private String translation;

    /** 赏析（卡片折叠时可选展开） */
    private String appreciation;

    /** 分配日期 YYYY-MM-DD，前端用于判断是否已看过 */
    private LocalDate date;
}
