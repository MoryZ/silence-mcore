package com.old.silence.mcore.client.content;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

/**
 * 广场新内容数 Feign 客户端（首页 Tier3 入口角标）
 * <p>
 * content-service 需实现端点：GET /api/v1/poetryNews/count
 * <p>
 * 返回指定时间后新增的资讯数，用于首页"广场"入口角标。
 * 不传 since 则返回总未读数。
 *
 * @author moryzang
 */
@FeignClient(name = "silence-content-service", contextId = "poetryNews", path = "/api/v1")
public interface PoetryNewsFeignClient {

    /**
     * 获取广场新内容数
     *
     * @param since ISO 8601 时间，不传则返回总未读数
     * @return 新增资讯数
     */
    @GetMapping("/poetryNews/count")
    Long countNew(@RequestParam(required = false) LocalDateTime since);
}
