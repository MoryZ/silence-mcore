package com.old.silence.mcore.api;

import java.math.BigInteger;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.old.silence.mcore.client.content.PoetryLearningContentFeignClient;
import com.old.silence.mcore.dto.PoetryLearningContentMcoreQuery;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.vo.PoetryLearningContentMcoreView;

/**
 * @author moryzang
 */
@RestController
@RequestMapping("/api/v1")
public class PoetryLearningContentResource {

    private final PoetryLearningContentFeignClient poetryLearningContentFeignClient;

    public PoetryLearningContentResource(PoetryLearningContentFeignClient poetryLearningContentFeignClient) {
        this.poetryLearningContentFeignClient = poetryLearningContentFeignClient;
    }

    @GetMapping(value = "/poetryLearningContents/count")
    public ApiResult<Long> countByCriteria(PoetryLearningContentMcoreQuery query) {
        return ApiResult.success(poetryLearningContentFeignClient.countByCriteria(query));
    }

    @GetMapping(value = "/poetryLearningContents/{id}")
    public ApiResult<PoetryLearningContentMcoreView> findById(@PathVariable BigInteger id) {
        var poetryLearningContentMcoreView = poetryLearningContentFeignClient.findById(id, PoetryLearningContentMcoreView.class).orElse(null);
        return ApiResult.success(poetryLearningContentMcoreView);
    }

    @GetMapping(value = "/poetryLearningContents")
    public ApiResult<List<PoetryLearningContentMcoreView>> findByIds(@RequestParam List<BigInteger> ids) {
        return ApiResult.success(poetryLearningContentFeignClient.findByIds(ids, PoetryLearningContentMcoreView.class));
    }
}
