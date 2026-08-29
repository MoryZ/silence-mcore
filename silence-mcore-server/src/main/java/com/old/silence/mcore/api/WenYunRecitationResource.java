package com.old.silence.mcore.api;

import com.old.silence.mcore.client.content.WenYunRecitationFeignClient;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.security.SilenceHallContextHolder;
import com.old.silence.mcore.vo.WenYunPageView;
import com.old.silence.mcore.vo.WenYunRecitationCreateRequest;
import com.old.silence.mcore.vo.WenYunRecitationStatsView;
import com.old.silence.mcore.vo.WenYunRecitationView;
import com.old.silence.mcore.vo.WenYunReviewRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/v1")
public class WenYunRecitationResource {

    private final WenYunRecitationFeignClient recitationFeignClient;

    public WenYunRecitationResource(WenYunRecitationFeignClient recitationFeignClient) {
        this.recitationFeignClient = recitationFeignClient;
    }

    private BigInteger currentUserId() {
        return SilenceHallContextHolder.getAuthenticatedUserId().orElse(BigInteger.ZERO);
    }

    @PostMapping("/recitation")
    public ApiResult<WenYunRecitationView> create(@RequestBody WenYunRecitationCreateRequest request) {
        request.setSubject(defaultSubject(request.getSubject()));
        return ApiResult.success(recitationFeignClient.create(request));
    }

    @GetMapping("/recitation")
    public ApiResult<WenYunPageView<WenYunRecitationView>> query(
            @RequestParam(defaultValue = "chinese") String subject,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "all") String tab,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "20") Integer size) {
        return ApiResult.success(recitationFeignClient.query(currentUserId(), subject, category, tab, page, size));
    }

    @GetMapping("/recitation/stats")
    public ApiResult<WenYunRecitationStatsView> stats() {
        return ApiResult.success(recitationFeignClient.stats(currentUserId()));
    }

    @PostMapping("/recitation/{id}/review")
    public ApiResult<WenYunRecitationView> review(@PathVariable BigInteger id, @RequestBody WenYunReviewRequest request) {
        return ApiResult.success(recitationFeignClient.review(id, request));
    }

    @PatchMapping("/recitation/{id}")
    public ApiResult<WenYunRecitationView> patch(@PathVariable BigInteger id, @RequestBody WenYunRecitationCreateRequest request) {
        return ApiResult.success(recitationFeignClient.patch(id, request));
    }

    @DeleteMapping("/recitation/{id}")
    public ApiResult<Void> delete(@PathVariable BigInteger id) {
        recitationFeignClient.delete(id);
        return ApiResult.success();
    }

    private String defaultSubject(String subject) {
        return subject == null ? "chinese" : subject;
    }
}
