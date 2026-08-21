package com.old.silence.mcore.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 广场新内容数视图（首页 Tier3 入口角标）
 * <p>
 * 对应接口契约：GET /api/v1/poetryNews/count
 * <p>
 * 降级策略：失败时角标隐藏。
 *
 * @author moryzang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsCountMcoreView {

    /** 指定时间后新增的资讯数 */
    private Long newCount;
}
