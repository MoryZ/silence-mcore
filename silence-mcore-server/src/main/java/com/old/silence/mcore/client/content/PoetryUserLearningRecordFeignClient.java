package com.old.silence.mcore.client.content;

import java.math.BigInteger;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import com.old.silence.mcore.dto.PoetryUserLearningRecordRequest;
import com.old.silence.web.bind.annotation.PostJsonMapping;

/**
 * @author moryzang
 */
@FeignClient(name = "silence-content-service", contextId = "poetryUserLearningRecord", path = "/api/v1")
public interface PoetryUserLearningRecordFeignClient {

    @PostJsonMapping("/poetryUserLearningRecords")
    BigInteger create(@RequestBody @Validated PoetryUserLearningRecordRequest command);


}
