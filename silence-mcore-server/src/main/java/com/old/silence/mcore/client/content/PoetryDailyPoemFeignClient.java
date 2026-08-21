package com.old.silence.mcore.client.content;

import com.old.silence.mcore.vo.PoetryDailyPoemMcoreView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

/**
 * 每日一诗 Feign 客户端
 * <p>
 * content-service 需实现端点：GET /api/v1/poetryDailyPoem
 * <p>
 * 业务规则：
 * - 每日分配一首，同一用户当天重复请求返回相同数据
 * - 可从用户已学范围或题库中随机，优先选未学过的
 * - 游客（userId=0）正常返回，不需登录
 *
 * @author moryzang
 */
@FeignClient(name = "silence-content-service", contextId = "poetryDailyPoem", path = "/api/v1")
public interface PoetryDailyPoemFeignClient {

    /**
     * 获取每日一诗
     *
     * @param userId 用户ID，游客传 BigInteger.ZERO
     * @return 每日一诗视图
     */
    @GetMapping("/poetryDailyPoem")
    PoetryDailyPoemMcoreView getDailyPoem(@RequestParam BigInteger userId);
}
