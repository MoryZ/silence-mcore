package com.old.silence.mcore.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 错题待复习数视图（首页 Tier3 入口角标）
 * <p>
 * 对应接口契约：GET /api/v1/poetryWrongQuestions/count
 * <p>
 * 降级策略：失败时角标隐藏。
 *
 * @author moryzang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrongQuestionCountMcoreVo {

    /** 当前用户待复习的错题数 */
    private Long pendingCount;
}
