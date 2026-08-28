package com.old.silence.mcore.api;

import com.old.silence.mcore.client.content.WenYunBookFeignClient;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.vo.WenYunBookItemView;
import com.old.silence.mcore.vo.WenYunBookView;
import com.old.silence.mcore.vo.WenYunPageView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class WenYunBookResource {

    private final WenYunBookFeignClient bookFeignClient;

    public WenYunBookResource(WenYunBookFeignClient bookFeignClient) {
        this.bookFeignClient = bookFeignClient;
    }

    @GetMapping("/wenyun/books")
    public ApiResult<List<WenYunBookView>> list() {
        return ApiResult.success(bookFeignClient.list());
    }

    @GetMapping("/wenyun/books/{id}/items")
    public ApiResult<WenYunPageView<WenYunBookItemView>> items(@PathVariable BigInteger id,
                                                               @RequestParam(defaultValue = "1") Integer page,
                                                               @RequestParam(defaultValue = "50") Integer size) {
        return ApiResult.success(bookFeignClient.items(id, page, size));
    }
}
