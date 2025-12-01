package com.old.silence.mcore.client.content;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import com.old.silence.content.api.vo.ContentView;
import com.old.silence.mcore.dto.ContentMcoreQuery;
import com.old.silence.web.data.ProjectedPayloadType;

/**
 * @author moryzang
 */
@FeignClient(name = "silence-content-service", contextId = "content", path = "/api/v1")
public interface ContentFeignClient {

    @GetMapping(value = "/poetryGrades", params = {"pageNo", "pageSize"})
    <T> Page<T> query(@Validated @SpringQueryMap ContentMcoreQuery query, Pageable pageable,
                      @ProjectedPayloadType(ContentView.class) Class<T> projectionType);
}
