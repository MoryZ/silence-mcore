package com.old.silence.mcore.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.old.silence.mcore.client.content.ContentFeignClient;
import com.old.silence.mcore.dto.ContentMcoreQuery;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.vo.ContentMcoreView;

/**
 * @author moryzang
 */
@RestController
@RequestMapping("/api/v1")
public class ContentResource {

    private final ContentFeignClient contentFeignClient;

    public ContentResource(ContentFeignClient contentFeignClient) {
        this.contentFeignClient = contentFeignClient;
    }

    @GetMapping("/contents")
    public ApiResult<Page<ContentMcoreView>> query(ContentMcoreQuery query, Pageable pageable) {
        return ApiResult.success(contentFeignClient.query(query, pageable, ContentMcoreView.class));
    }

}
