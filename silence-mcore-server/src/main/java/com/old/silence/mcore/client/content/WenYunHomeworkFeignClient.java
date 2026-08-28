package com.old.silence.mcore.client.content;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.old.silence.mcore.vo.WenYunHomeworkCreateRequest;
import com.old.silence.mcore.vo.WenYunHomeworkStatsView;
import com.old.silence.mcore.vo.WenYunHomeworkView;
import com.old.silence.mcore.vo.WenYunPageView;

import java.math.BigInteger;

@FeignClient(name = "silence-content-service", contextId = "wenyun-homework", path = "/api/v1")
public interface WenYunHomeworkFeignClient {

    @PostMapping("/wenyun/homework")
    WenYunHomeworkView create(@RequestBody WenYunHomeworkCreateRequest request);

    @GetMapping("/wenyun/homework")
    WenYunPageView<WenYunHomeworkView> query(@RequestParam BigInteger userId,
                                              @RequestParam String subject,
                                              @RequestParam String status,
                                              @RequestParam String tab,
                                              @RequestParam(defaultValue = "1") Integer page,
                                              @RequestParam(defaultValue = "20") Integer size);

    @GetMapping("/wenyun/homework/stats")
    WenYunHomeworkStatsView stats(@RequestParam BigInteger userId);

    @PatchMapping("/wenyun/homework/{id}")
    WenYunHomeworkView patch(@PathVariable BigInteger id, @RequestBody WenYunHomeworkCreateRequest request);

    @DeleteMapping("/wenyun/homework/{id}")
    void delete(@PathVariable BigInteger id);
}
