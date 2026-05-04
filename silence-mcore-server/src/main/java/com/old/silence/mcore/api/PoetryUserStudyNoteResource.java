package com.old.silence.mcore.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.old.silence.core.context.CommonErrors;
import com.old.silence.mcore.client.content.PoetryUserStudyNoteFeignClient;
import com.old.silence.mcore.dto.PoetryUserStudyNoteRequest;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.security.SilenceHallContextHolder;
import com.old.silence.mcore.vo.PoetryUserStudyNoteMcoreView;

import java.math.BigInteger;
import java.util.List;


/**
 * PoetryUserStudyNote资源控制器
 */
@RestController
@RequestMapping("/api/v1")
public class PoetryUserStudyNoteResource {
    private final PoetryUserStudyNoteFeignClient poetryUserStudyNoteFeignClient;

    public PoetryUserStudyNoteResource(PoetryUserStudyNoteFeignClient poetryUserStudyNoteFeignClient) {
        this.poetryUserStudyNoteFeignClient = poetryUserStudyNoteFeignClient;
    }

    @GetMapping("/poetryUserStudyNotes/{contentId}")
    public ApiResult<List<PoetryUserStudyNoteMcoreView>> findByContentIdAndUserId(@PathVariable BigInteger contentId) {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElseThrow(CommonErrors.ACCESS_DENIED::createException);
        return ApiResult.success(poetryUserStudyNoteFeignClient.findByContentIdAndUserId(contentId, userId, PoetryUserStudyNoteMcoreView.class));
    }

    @PostMapping(value = "/poetryUserStudyNotes")
    public ApiResult<String> create(@RequestBody PoetryUserStudyNoteRequest poetryUserStudyNoteCommand) {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElseThrow(CommonErrors.ACCESS_DENIED::createException);
        poetryUserStudyNoteCommand.setUserId(userId);
        return ApiResult.success(String.valueOf(poetryUserStudyNoteFeignClient.create(poetryUserStudyNoteCommand)));
    }

    @PutMapping("/poetryUserStudyNotes/{id}")
    public ApiResult<Boolean> update(@PathVariable BigInteger id, @RequestBody PoetryUserStudyNoteRequest poetryUserStudyNoteCommand) {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElseThrow(CommonErrors.ACCESS_DENIED::createException);
        poetryUserStudyNoteCommand.setUserId(userId);
        poetryUserStudyNoteFeignClient.update(id, poetryUserStudyNoteCommand);
        return ApiResult.success(true);
    }

    @DeleteMapping("/poetryUserStudyNotes/{id}")
    public ApiResult<Boolean> deleteById(@PathVariable BigInteger id) {
        poetryUserStudyNoteFeignClient.deleteById(id);
        return ApiResult.success(true);
    }
}