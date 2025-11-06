package com.old.silence.mcore.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.old.silence.mcore.client.content.PoetryCategoryFeignClient;
import com.old.silence.mcore.client.content.PoetryGradeFeignClient;
import com.old.silence.mcore.dto.PoetryCategoryMcoreQuery;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.vo.PoetryCategoryMcoreView;
import com.old.silence.mcore.vo.PoetryGradeMcoreView;

/**
 * @author moryzang
 */
@RestController
@RequestMapping("/api/v1")
public class PoetryCategoryResource {

    private final PoetryCategoryFeignClient poetryCategoryFeignClient;

    public PoetryCategoryResource(PoetryCategoryFeignClient poetryCategoryFeignClient) {
        this.poetryCategoryFeignClient = poetryCategoryFeignClient;
    }

    @GetMapping("/poetryCategories")
    public ApiResult<Page<PoetryCategoryMcoreView>> query(PoetryCategoryMcoreQuery query, Pageable pageable) {
        return ApiResult.success(poetryCategoryFeignClient.query(query, pageable, PoetryCategoryMcoreView.class));
    }

}
