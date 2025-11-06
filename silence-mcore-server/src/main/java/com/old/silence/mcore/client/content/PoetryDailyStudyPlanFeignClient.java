package com.old.silence.mcore.client.content;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.old.silence.content.api.vo.PoetryDailyStudyPlanView;
import com.old.silence.web.data.ProjectedPayloadType;

/**
 * @author moryzang
 */

@FeignClient(name = "silence-content-service", contextId = "poetryDailyStudyPlan", path = "/api/v1")
public interface PoetryDailyStudyPlanFeignClient {

    @GetMapping(value = "/poetryDailyStudyPlans/{subCategoryId}/{userId}")
    <T> Optional<T> findByUserIdAndSubCategoryIdAndPlanDate(@PathVariable BigInteger userId, @PathVariable BigInteger subCategoryId, @RequestParam LocalDate planDate,
                                                            @ProjectedPayloadType(PoetryDailyStudyPlanView.class) Class<T> projectionType);

    @GetMapping(value = "/poetryDailyStudyPlans/{userId}")
    <T> List<T> findByUserIdAndPlanDate(@PathVariable BigInteger userId, @RequestParam LocalDate planDate,
                                        @ProjectedPayloadType(PoetryDailyStudyPlanView.class) Class<T> projectionType);
}
