package com.old.silence.mcore.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.old.silence.core.context.CommonErrors;
import com.old.silence.mcore.client.content.PoetryAnswerRecordsFeignClient;
import com.old.silence.mcore.dto.PoetryAnswerRecordsRequest;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.security.SilenceHallContextHolder;
import com.old.silence.mcore.vo.PoetryAnswerRecordsMcoreView;

import java.math.BigInteger;
import java.util.List;

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

    @GetMapping(value = "/poetryAnswerRecords/{contentId}/{subCategoryId}")
    public ApiResult<List<PoetryAnswerRecordsMcoreView>> findByContentIdAndUserId(@PathVariable BigInteger contentId, @PathVariable BigInteger subCategoryId) {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElseThrow(CommonErrors.ACCESS_DENIED::createException);
        return ApiResult.success(poetryAnswerRecordsFeignClient.findByContentIdAndUserId(contentId, subCategoryId, userId, PoetryAnswerRecordsMcoreView.class));
    }


    @PostMapping("/poetryAnswerRecords")
    public ApiResult<String> create(@RequestBody PoetryAnswerRecordsRequest poetryAnswerRecordsRequest) {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElseThrow(CommonErrors.ACCESS_DENIED::createException);
        poetryAnswerRecordsRequest.setUserId(userId);
        return ApiResult.success(String.valueOf(poetryAnswerRecordsFeignClient.create(poetryAnswerRecordsRequest)));
    }

}
