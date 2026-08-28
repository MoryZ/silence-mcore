package com.old.silence.mcore.client.content;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.old.silence.mcore.vo.WenYunBookItemView;
import com.old.silence.mcore.vo.WenYunBookView;
import com.old.silence.mcore.vo.WenYunPageView;

import java.math.BigInteger;
import java.util.List;

@FeignClient(name = "silence-content-service", contextId = "wenyun-book", path = "/api/v1")
public interface WenYunBookFeignClient {

    @GetMapping("/wenyun/books")
    List<WenYunBookView> list();

    @GetMapping("/wenyun/books/{id}/items")
    WenYunPageView<WenYunBookItemView> items(@PathVariable BigInteger id,
                                             @RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(defaultValue = "50") Integer size);
}
