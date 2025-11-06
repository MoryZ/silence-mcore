package com.old.silence.mcore.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.old.silence.core.context.CommonErrors;
import com.old.silence.mcore.client.content.PoetryAnswerRecordsFeignClient;
import com.old.silence.mcore.dto.PoetryAnswerRecordsRequest;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.security.SilenceHallContextHolder;

/**
 * @author moryzang
 */
@RestController
@RequestMapping("/api/v1")
public class PoetryAnswerRecordsResource {

    private final PoetryAnswerRecordsFeignClient poetryAnswerRecordsFeignClient;

    public PoetryAnswerRecordsResource(PoetryAnswerRecordsFeignClient poetryAnswerRecordsFeignClient) {
        this.poetryAnswerRecordsFeignClient = poetryAnswerRecordsFeignClient;
    }

    @PostMapping("/poetryAnswerRecords")
    public ApiResult<String> create(@RequestBody PoetryAnswerRecordsRequest poetryAnswerRecordsRequest) {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElseThrow(CommonErrors.ACCESS_DENIED::createException);
        poetryAnswerRecordsRequest.setUserId(userId);
        return ApiResult.success(String.valueOf(poetryAnswerRecordsFeignClient.create(poetryAnswerRecordsRequest)));
    }

}
