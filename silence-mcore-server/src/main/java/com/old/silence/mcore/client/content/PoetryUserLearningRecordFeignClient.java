package com.old.silence.mcore.client.content;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import com.old.silence.mcore.dto.PoetryUserLearningRecordRequest;
import com.old.silence.web.bind.annotation.PostJsonMapping;

import java.math.BigInteger;

/**
 * @author moryzang
 */
@FeignClient(name = "silence-content-service", contextId = "poetryUserLearningRecord", path = "/api/v1")
public interface PoetryUserLearningRecordFeignClient {

    @PostJsonMapping("/poetryUserLearningRecords")
    BigInteger completeLearningRecord(@RequestBody @Validated PoetryUserLearningRecordRequest command);


}
