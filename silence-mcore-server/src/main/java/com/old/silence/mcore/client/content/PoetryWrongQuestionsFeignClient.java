package com.old.silence.mcore.client.content;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

/**
 * 错题待复习数 Feign 客户端（首页 Tier3 入口角标）
 * <p>
 * content-service 需实现端点：GET /api/v1/poetryWrongQuestions/count
 * <p>
 * 返回当前用户待复习的错题数，用于首页"错题本"入口角标。
 * 游客（userId=0）返回 0。
 *
 * @author moryzang
 */
@FeignClient(name = "silence-content-service", contextId = "poetryWrongQuestions", path = "/api/v1")
public interface PoetryWrongQuestionsFeignClient {

    /**
     * 获取待复习错题数
     *
     * @param userId 用户ID，游客传 BigInteger.ZERO
     * @return 待复习错题数
     */
    @GetMapping("/poetryWrongQuestions/count")
    Long countPending(@RequestParam BigInteger userId);
}
