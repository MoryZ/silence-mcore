package com.old.silence.mcore.client.content;

import java.math.BigInteger;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.old.silence.content.api.vo.PoetryUserFavoriteView;
import com.old.silence.mcore.dto.PoetryUserFavoriteMcoreQuery;
import com.old.silence.mcore.dto.PoetryUserFavoriteRequest;
import com.old.silence.web.bind.annotation.PostJsonMapping;
import com.old.silence.web.data.ProjectedPayloadType;

/**
 * @author moryzang
 */

@FeignClient(name = "silence-content-service", contextId = "poetryUserFavorite", path = "/api/v1")
public interface PoetryUserFavoriteFeignClient {

    @GetMapping(value = "/poetryUserFavorites", params = {"pageNo", "pageSize"})
    <T> Page<T> query(@Validated @SpringQueryMap PoetryUserFavoriteMcoreQuery query, Pageable pageable,
                      @ProjectedPayloadType(PoetryUserFavoriteView.class) Class<T> projectionType);

    @PostJsonMapping("/poetryUserFavorites")
    BigInteger create(@RequestBody @Validated PoetryUserFavoriteRequest command);


    @DeleteMapping("/poetryUserFavorites/{id}")
    void deleteById(@PathVariable BigInteger id);
}
