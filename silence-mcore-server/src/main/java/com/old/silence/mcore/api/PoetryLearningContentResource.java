package com.old.silence.mcore.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.old.silence.mcore.client.content.PoetryLearningContentFeignClient;
import com.old.silence.mcore.dto.PoetryLearningContentMcoreQuery;

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
    public long countByCriteria(PoetryLearningContentMcoreQuery query) {
        return poetryLearningContentFeignClient.countByCriteria(query);
    }
}
