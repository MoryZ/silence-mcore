package com.old.silence.mcore.client.content;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import com.old.silence.content.api.vo.PoetryCategoryView;
import com.old.silence.mcore.dto.PoetryCategoryMcoreQuery;
import com.old.silence.web.data.ProjectedPayloadType;

/**
 * @author moryzang
 */
@FeignClient(name = "silence-content-service", contextId = "poetryCategory", path = "/api/v1")
public interface PoetryCategoryFeignClient {

    @GetMapping(value = "/poetryCategories", params = {"pageNo", "pageSize"})
    <T> Page<T> query(@Validated @SpringQueryMap PoetryCategoryMcoreQuery query, Pageable pageable,
                      @ProjectedPayloadType(PoetryCategoryView.class) Class<T> projectionType);
}
