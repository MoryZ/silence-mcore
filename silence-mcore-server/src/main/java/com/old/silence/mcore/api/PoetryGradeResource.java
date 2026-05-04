package com.old.silence.mcore.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.old.silence.mcore.client.content.PoetryGradeFeignClient;
import com.old.silence.mcore.dto.PoetryGradeMcoreQuery;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.vo.PoetryGradeMcoreView;

/**
 * @author moryzang
 */
@RestController
@RequestMapping("/api/v1")
public class PoetryGradeResource {

    private final PoetryGradeFeignClient poetryGradeFeignClient;

    public PoetryGradeResource(PoetryGradeFeignClient poetryGradeFeignClient) {
        this.poetryGradeFeignClient = poetryGradeFeignClient;
    }

    @GetMapping("/poetryGrades")
    public ApiResult<Page<PoetryGradeMcoreView>> query(PoetryGradeMcoreQuery query, Pageable pageable) {
        query.setEnabled(true);
        return ApiResult.success(poetryGradeFeignClient.query(query, pageable, PoetryGradeMcoreView.class));
    }

}
