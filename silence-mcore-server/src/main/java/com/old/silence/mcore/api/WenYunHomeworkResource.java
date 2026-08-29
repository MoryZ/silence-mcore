package com.old.silence.mcore.api;

import com.old.silence.mcore.client.content.WenYunHomeworkFeignClient;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.security.SilenceHallContextHolder;
import com.old.silence.mcore.vo.WenYunHomeworkCreateRequest;
import com.old.silence.mcore.vo.WenYunHomeworkStatsView;
import com.old.silence.mcore.vo.WenYunHomeworkView;
import com.old.silence.mcore.vo.WenYunPageView;
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
public class WenYunHomeworkResource {

    private final WenYunHomeworkFeignClient homeworkFeignClient;

    public WenYunHomeworkResource(WenYunHomeworkFeignClient homeworkFeignClient) {
        this.homeworkFeignClient = homeworkFeignClient;
    }

    private BigInteger currentUserId() {
        return SilenceHallContextHolder.getAuthenticatedUserId().orElse(BigInteger.ZERO);
    }

    @PostMapping("/homework")
    public ApiResult<WenYunHomeworkView> create(@RequestBody WenYunHomeworkCreateRequest request) {
        request.setSubject(defaultSubject(request.getSubject()));
        return ApiResult.success(homeworkFeignClient.create(request));
    }

    @GetMapping("/homework")
    public ApiResult<WenYunPageView<WenYunHomeworkView>> query(
            @RequestParam(defaultValue = "chinese") String subject,
            @RequestParam(defaultValue = "all") String status,
            @RequestParam(defaultValue = "open") String tab,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "20") Integer size) {
        return ApiResult.success(homeworkFeignClient.query(currentUserId(), subject, status, tab, page, size));
    }

    @GetMapping("/homework/stats")
    public ApiResult<WenYunHomeworkStatsView> stats() {
        return ApiResult.success(homeworkFeignClient.stats(currentUserId()));
    }

    @PatchMapping("/homework/{id}")
    public ApiResult<WenYunHomeworkView> patch(@PathVariable BigInteger id, @RequestBody WenYunHomeworkCreateRequest request) {
        return ApiResult.success(homeworkFeignClient.patch(id, request));
    }

    @DeleteMapping("/homework/{id}")
    public ApiResult<Void> delete(@PathVariable BigInteger id) {
        homeworkFeignClient.delete(id);
        return ApiResult.success();
    }

    private String defaultSubject(String subject) {
        return subject == null ? "chinese" : subject;
    }
}
