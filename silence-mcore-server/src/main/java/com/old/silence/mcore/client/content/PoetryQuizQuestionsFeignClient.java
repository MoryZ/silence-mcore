package com.old.silence.mcore.client.content;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import com.old.silence.content.api.vo.PoetryQuizQuestionsView;
import com.old.silence.mcore.dto.PoetryQuizQuestionsMcoreQuery;
import com.old.silence.web.data.ProjectedPayloadType;

/**
 * @author moryzang
 */
@FeignClient(name = "silence-content-service", contextId = "poetry-quiz-questions", path = "/api/v1")
public interface PoetryQuizQuestionsFeignClient {

    @GetMapping(value = "/poetryQuizQuestions", params = {"pageNo", "pageSize"})
    <T> Page<T> query(@Validated @SpringQueryMap PoetryQuizQuestionsMcoreQuery query, Pageable pageable,
                      @ProjectedPayloadType(PoetryQuizQuestionsView.class) Class<T> projectionType);
}
