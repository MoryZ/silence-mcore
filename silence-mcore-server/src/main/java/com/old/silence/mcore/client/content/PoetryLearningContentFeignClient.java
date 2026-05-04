package com.old.silence.mcore.client.content;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.old.silence.content.api.vo.PoetryLearningContentView;
import com.old.silence.mcore.dto.PoetryLearningContentMcoreQuery;
import com.old.silence.web.data.ProjectedPayloadType;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * @author moryzang
 */
@FeignClient(name = "silence-content-service", contextId = "poetryLearningContent", path = "/api/v1")
public interface PoetryLearningContentFeignClient {

    @GetMapping("/poetryLearningContents/count")
    long countByCriteria(@Validated @SpringQueryMap PoetryLearningContentMcoreQuery poetryLearningContentMcoreQuery);

    @GetMapping("/poetryLearningContents/{id}")
    <T> Optional<T> findById(@PathVariable BigInteger id, @ProjectedPayloadType(PoetryLearningContentView.class) Class<T> projectionType);

    @GetMapping("/poetryLearningContents")
    <T> List<T> findByIds(@RequestParam List<BigInteger> ids, @ProjectedPayloadType(PoetryLearningContentView.class) Class<T> projectionType);
}
