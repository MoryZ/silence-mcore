package com.old.silence.mcore.client.content;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import com.old.silence.content.api.dto.PoetryLearningContentQuery;
import com.old.silence.content.api.vo.PoetryCategoryView;
import com.old.silence.mcore.dto.PoetryCategoryMcoreQuery;
import com.old.silence.mcore.dto.PoetryLearningContentMcoreQuery;
import com.old.silence.web.data.ProjectedPayloadType;

/**
 * @author moryzang
 */
@FeignClient(name = "silence-content-service", contextId = "poetryLearningContent", path = "/api/v1")
public interface PoetryLearningContentFeignClient {

    @GetMapping(value = "/poetryLearningContents/count")
    long countByCriteria(PoetryLearningContentMcoreQuery poetryLearningContentMcoreQuery);
}
