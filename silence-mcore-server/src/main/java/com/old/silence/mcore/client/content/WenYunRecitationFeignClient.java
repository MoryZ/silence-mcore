package com.old.silence.mcore.client.content;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.old.silence.mcore.vo.WenYunPageView;
import com.old.silence.mcore.vo.WenYunRecitationCreateRequest;
import com.old.silence.mcore.vo.WenYunRecitationStatsView;
import com.old.silence.mcore.vo.WenYunRecitationView;
import com.old.silence.mcore.vo.WenYunReviewRequest;

import java.math.BigInteger;

@FeignClient(name = "silence-content-service", contextId = "wenyun-recitation", path = "/api/v1")
public interface WenYunRecitationFeignClient {

    @PostMapping("/wenyun/recitation")
    WenYunRecitationView create(@RequestBody WenYunRecitationCreateRequest request);

    @GetMapping("/wenyun/recitation")
    WenYunPageView<WenYunRecitationView> query(@RequestParam BigInteger userId,
                                               @RequestParam String subject,
                                               @RequestParam String category,
                                               @RequestParam String tab,
                                               @RequestParam(defaultValue = "1") Integer page,
                                               @RequestParam(defaultValue = "20") Integer size);

    @GetMapping("/wenyun/recitation/stats")
    WenYunRecitationStatsView stats(@RequestParam BigInteger userId);

    @PostMapping("/wenyun/recitation/{id}/review")
    WenYunRecitationView review(@PathVariable BigInteger id, @RequestBody WenYunReviewRequest request);

    @PatchMapping("/wenyun/recitation/{id}")
    WenYunRecitationView patch(@PathVariable BigInteger id, @RequestBody WenYunRecitationCreateRequest request);

    @DeleteMapping("/wenyun/recitation/{id}")
    void delete(@PathVariable BigInteger id);
}
