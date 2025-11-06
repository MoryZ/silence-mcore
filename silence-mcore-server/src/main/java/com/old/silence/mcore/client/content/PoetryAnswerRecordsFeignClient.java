package com.old.silence.mcore.client.content;

import java.math.BigInteger;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import com.old.silence.mcore.dto.PoetryAnswerRecordsRequest;
import com.old.silence.web.bind.annotation.PostJsonMapping;

/**
 * @author moryzang
 */
@FeignClient(name = "silence-content-service", contextId = "poetry-answer-records", path = "/api/v1")
public interface PoetryAnswerRecordsFeignClient  {

    @PostJsonMapping("/poetryAnswerRecords")
    BigInteger create(@RequestBody @Validated PoetryAnswerRecordsRequest command);


}
