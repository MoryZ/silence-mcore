package com.old.silence.mcore.client.content;

import java.math.BigInteger;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.old.silence.content.api.vo.PoetryAnswerRecordsView;
import com.old.silence.mcore.dto.PoetryAnswerRecordsRequest;
import com.old.silence.web.bind.annotation.PostJsonMapping;
import com.old.silence.web.data.ProjectedPayloadType;

/**
 * @author moryzang
 */
@FeignClient(name = "silence-content-service", contextId = "poetry-answer-records", path = "/api/v1")
public interface PoetryAnswerRecordsFeignClient  {

    @GetMapping(value = "/poetryAnswerRecords/{contentId}/{subcategoryId}/{userId}")
    <T> List<T> findByContentIdAndUserId(@PathVariable BigInteger contentId, @PathVariable BigInteger subcategoryId, @PathVariable BigInteger userId,
                                         @ProjectedPayloadType(PoetryAnswerRecordsView.class) Class<T> projectionType);

    @PostJsonMapping("/poetryAnswerRecords")
    BigInteger create(@RequestBody @Validated PoetryAnswerRecordsRequest command);


}
