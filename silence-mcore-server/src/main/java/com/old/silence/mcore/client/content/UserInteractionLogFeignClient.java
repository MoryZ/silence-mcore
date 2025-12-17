package com.old.silence.mcore.client.content;

import java.math.BigInteger;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.old.silence.content.api.vo.UserInteractionLogView;
import com.old.silence.mcore.dto.UserInteractionLogMcoreQuery;
import com.old.silence.mcore.dto.UserInteractionLogRequest;
import com.old.silence.web.bind.annotation.PostJsonMapping;
import com.old.silence.web.data.ProjectedPayloadType;

/**
 * @author moryzang
 */

@FeignClient(name = "silence-content-service", contextId = "userInteractionLog", path = "/api/v1")
public interface UserInteractionLogFeignClient {

    @GetMapping(value = "/userInteractionLogs", params = {"pageNo", "pageSize"})
    <T> Page<T> query(@Validated @SpringQueryMap UserInteractionLogMcoreQuery query, Pageable pageable,
                      @ProjectedPayloadType(UserInteractionLogView.class) Class<T> projectionType);

    @PostJsonMapping("/userInteractionLogs")
    BigInteger create(@RequestBody @Validated UserInteractionLogRequest command);


    @DeleteMapping("/userInteractionLogs/{id}")
    void deleteById(@PathVariable BigInteger id);
}
