package com.old.silence.mcore.client.content;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.old.silence.content.api.vo.ContentInteractionLogView;
import com.old.silence.mcore.dto.ContentUserInteractionLogMcoreQuery;
import com.old.silence.mcore.dto.ContentUserInteractionLogRequest;
import com.old.silence.web.bind.annotation.PostJsonMapping;
import com.old.silence.web.data.ProjectedPayloadType;

import java.math.BigInteger;

/**
 * @author moryzang
 */

@FeignClient(name = "silence-content-service", contextId = "contentInteractionLog", path = "/api/v1")
public interface UserInteractionLogFeignClient {

    @GetMapping(value = "/contentInteractionLogs", params = {"pageNo", "pageSize"})
    <T> Page<T> query(@Validated @SpringQueryMap ContentUserInteractionLogMcoreQuery query, Pageable pageable,
                      @ProjectedPayloadType(ContentInteractionLogView.class) Class<T> projectionType);

    @PostJsonMapping("/contentInteractionLogs")
    BigInteger create(@RequestBody @Validated ContentUserInteractionLogRequest command);


    @DeleteMapping("/contentInteractionLogs/{id}")
    void deleteById(@PathVariable BigInteger id);
}
