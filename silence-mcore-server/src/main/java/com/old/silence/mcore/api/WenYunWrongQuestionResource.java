package com.old.silence.mcore.api;

import com.old.silence.mcore.client.content.WenYunWrongQuestionFeignClient;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.security.SilenceHallContextHolder;
import com.old.silence.mcore.vo.WenYunPageView;
import com.old.silence.mcore.vo.WenYunReviewRequest;
import com.old.silence.mcore.vo.WenYunWrongQuestionCreateRequest;
import com.old.silence.mcore.vo.WenYunWrongQuestionStatsView;
import com.old.silence.mcore.vo.WenYunWrongQuestionView;
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
public class WenYunWrongQuestionResource {

    private final WenYunWrongQuestionFeignClient wrongQuestionFeignClient;

    public WenYunWrongQuestionResource(WenYunWrongQuestionFeignClient wrongQuestionFeignClient) {
        this.wrongQuestionFeignClient = wrongQuestionFeignClient;
    }

    private BigInteger currentUserId() {
        return SilenceHallContextHolder.getAuthenticatedUserId().orElse(BigInteger.ZERO);
    }

    @PostMapping("/wenyun/wrong-questions")
    public ApiResult<WenYunWrongQuestionView> create(@RequestBody WenYunWrongQuestionCreateRequest request) {
        request.setSubject(defaultSubject(request.getSubject()));
        return ApiResult.success(wrongQuestionFeignClient.create(request));
    }

    @GetMapping("/wenyun/wrong-questions")
    public ApiResult<WenYunPageView<WenYunWrongQuestionView>> query(
            @RequestParam(defaultValue = "chinese") String subject,
            @RequestParam(required = false) Integer stage,
            @RequestParam(required = false) Boolean graduated,
            @RequestParam(defaultValue = "all") String tab,
            @RequestParam(required = false) String wrongReason,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "20") Integer size) {
        return ApiResult.success(wrongQuestionFeignClient.query(currentUserId(), subject, stage, graduated, tab, wrongReason, page, size));
    }

    @GetMapping("/wenyun/wrong-questions/stats")
    public ApiResult<WenYunWrongQuestionStatsView> stats() {
        return ApiResult.success(wrongQuestionFeignClient.stats(currentUserId()));
    }

    @PostMapping("/wenyun/wrong-questions/{id}/review")
    public ApiResult<WenYunWrongQuestionView> review(@PathVariable BigInteger id, @RequestBody WenYunReviewRequest request) {
        return ApiResult.success(wrongQuestionFeignClient.review(id, request));
    }

    @PatchMapping("/wenyun/wrong-questions/{id}")
    public ApiResult<WenYunWrongQuestionView> patch(@PathVariable BigInteger id, @RequestBody WenYunWrongQuestionCreateRequest request) {
        return ApiResult.success(wrongQuestionFeignClient.patch(id, request));
    }

    @DeleteMapping("/wenyun/wrong-questions/{id}")
    public ApiResult<Void> delete(@PathVariable BigInteger id) {
        wrongQuestionFeignClient.delete(id);
        return ApiResult.success();
    }

    private String defaultSubject(String subject) {
        return subject == null ? "chinese" : subject;
    }
}
