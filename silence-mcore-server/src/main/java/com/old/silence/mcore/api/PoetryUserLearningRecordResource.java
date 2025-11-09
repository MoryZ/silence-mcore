package com.old.silence.mcore.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.old.silence.core.context.CommonErrors;
import com.old.silence.mcore.client.content.PoetryUserLearningRecordFeignClient;
import com.old.silence.mcore.dto.PoetryUserLearningRecordRequest;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.security.SilenceHallContextHolder;

/**
 * @author moryzang
 */
@RestController
@RequestMapping("/api/v1")
public class PoetryUserLearningRecordResource {

    private final PoetryUserLearningRecordFeignClient poetryUserLearningRecordFeignClient;

    public PoetryUserLearningRecordResource(PoetryUserLearningRecordFeignClient poetryUserLearningRecordFeignClient) {
        this.poetryUserLearningRecordFeignClient = poetryUserLearningRecordFeignClient;
    }

    @PostMapping("/poetryUserLearningRecords")
    public ApiResult<String> create(@RequestBody PoetryUserLearningRecordRequest poetryAnswerRecordsRequest) {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElseThrow(CommonErrors.ACCESS_DENIED::createException);
        poetryAnswerRecordsRequest.setUserId(userId);
        return ApiResult.success(String.valueOf(poetryUserLearningRecordFeignClient.create(poetryAnswerRecordsRequest)));
    }

}
