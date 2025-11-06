package com.old.silence.mcore.api;

import java.math.BigInteger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.old.silence.core.context.CommonErrors;
import com.old.silence.mcore.client.content.PoetryUserFavoriteFeignClient;
import com.old.silence.mcore.dto.PoetryUserFavoriteMcoreQuery;
import com.old.silence.mcore.dto.PoetryUserFavoriteRequest;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.security.SilenceHallContextHolder;
import com.old.silence.mcore.vo.PoetryUserFavoriteMcoreView;


/**
 * PoetryUserStudyNote资源控制器
 */
@RestController
@RequestMapping("/api/v1")
public class PoetryUserFavoriteResource {
    private final PoetryUserFavoriteFeignClient poetryUserFavoriteFeignClient;

    public PoetryUserFavoriteResource(PoetryUserFavoriteFeignClient poetryUserFavoriteFeignClient) {
        this.poetryUserFavoriteFeignClient = poetryUserFavoriteFeignClient;
    }

    @GetMapping("/poetryUserFavorites")
    public ApiResult<Page<PoetryUserFavoriteMcoreView>> query(PoetryUserFavoriteMcoreQuery query, Pageable pageable) {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElseThrow(CommonErrors.ACCESS_DENIED::createException);
        query.setUserId(userId);
        return ApiResult.success(poetryUserFavoriteFeignClient.query(query, pageable, PoetryUserFavoriteMcoreView.class));
    }


    @PostMapping(value = "/poetryUserFavorites")
    public ApiResult<String> create(@RequestBody PoetryUserFavoriteRequest poetryUserFavoriteRequest) {
        var userId = SilenceHallContextHolder.getAuthenticatedUserId().orElseThrow(CommonErrors.ACCESS_DENIED::createException);
        poetryUserFavoriteRequest.setUserId(userId);
        return ApiResult.success(String.valueOf(poetryUserFavoriteFeignClient.create(poetryUserFavoriteRequest)));
    }


    @DeleteMapping("/poetryUserFavorites/{id}")
    public ApiResult<Boolean> deleteById(@PathVariable BigInteger id) {
        poetryUserFavoriteFeignClient.deleteById(id);
        return ApiResult.success(true);
    }
}