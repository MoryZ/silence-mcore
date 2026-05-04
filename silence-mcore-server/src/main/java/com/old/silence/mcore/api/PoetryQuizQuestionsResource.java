package com.old.silence.mcore.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.old.silence.mcore.client.content.PoetryQuizQuestionsFeignClient;
import com.old.silence.mcore.dto.PoetryQuizQuestionsMcoreQuery;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.vo.PoetryQuizQuestionsMcoreView;

/**
 * @author moryzang
 */
@RestController
@RequestMapping("/api/v1")
public class PoetryQuizQuestionsResource {

    private final PoetryQuizQuestionsFeignClient poetryQuizQuestionsFeignClient;

    public PoetryQuizQuestionsResource(PoetryQuizQuestionsFeignClient poetryQuizQuestionsFeignClient) {
        this.poetryQuizQuestionsFeignClient = poetryQuizQuestionsFeignClient;
    }

    @GetMapping("/poetryQuizQuestions")
    public ApiResult<Page<PoetryQuizQuestionsMcoreView>> query(PoetryQuizQuestionsMcoreQuery query, Pageable pageable) {
        return ApiResult.success(poetryQuizQuestionsFeignClient.query(query, pageable, PoetryQuizQuestionsMcoreView.class));
    }

}
