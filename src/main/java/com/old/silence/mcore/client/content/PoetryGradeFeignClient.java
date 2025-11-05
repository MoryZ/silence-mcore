package com.old.silence.mcore.client.content;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import com.old.silence.content.api.vo.PoetryGradeView;
import com.old.silence.mcore.dto.PoetryGradeMcoreQuery;
import com.old.silence.web.data.ProjectedPayloadType;

/**
 * @author moryzang
 */
@FeignClient(name = "silence-content-service", contextId = "poetryGrade", path = "/api/v1")
public interface PoetryGradeFeignClient {

    @GetMapping(value = "/poetryGrades", params = {"pageNo", "pageSize"})
    <T> Page<T> query(@Validated @SpringQueryMap PoetryGradeMcoreQuery query, Pageable pageable,
                      @ProjectedPayloadType(PoetryGradeView.class) Class<T> projectionType);
}
