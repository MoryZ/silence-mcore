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
import com.old.silence.mcore.vo.WenYunReviewRequest;
import com.old.silence.mcore.vo.WenYunWrongQuestionCreateRequest;
import com.old.silence.mcore.vo.WenYunWrongQuestionStatsView;
import com.old.silence.mcore.vo.WenYunWrongQuestionView;

import java.math.BigInteger;

@FeignClient(name = "silence-content-service", contextId = "wenyun-wrong-question", path = "/api/v1")
public interface WenYunWrongQuestionFeignClient {

    @PostMapping("/wenyun/wrong-questions")
    WenYunWrongQuestionView create(@RequestBody WenYunWrongQuestionCreateRequest request);

    @GetMapping("/wenyun/wrong-questions")
    WenYunPageView<WenYunWrongQuestionView> query(@RequestParam BigInteger userId,
                                                  @RequestParam String subject,
                                                  @RequestParam Integer stage,
                                                  @RequestParam Boolean graduated,
                                                  @RequestParam String tab,
                                                  @RequestParam String wrongReason,
                                                  @RequestParam(defaultValue = "1") Integer page,
                                                  @RequestParam(defaultValue = "20") Integer size);

    @GetMapping("/wenyun/wrong-questions/stats")
    WenYunWrongQuestionStatsView stats(@RequestParam BigInteger userId);

    @PostMapping("/wenyun/wrong-questions/{id}/review")
    WenYunWrongQuestionView review(@PathVariable BigInteger id, @RequestBody WenYunReviewRequest request);

    @PatchMapping("/wenyun/wrong-questions/{id}")
    WenYunWrongQuestionView patch(@PathVariable BigInteger id, @RequestBody WenYunWrongQuestionCreateRequest request);

    @DeleteMapping("/wenyun/wrong-questions/{id}")
    void delete(@PathVariable BigInteger id);
}
