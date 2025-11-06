package com.old.silence.mcore.client.content;


import java.math.BigInteger;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.old.silence.content.api.dto.PoetryUserStudySettingCommand;
import com.old.silence.content.api.vo.PoetryGradeView;
import com.old.silence.content.api.vo.PoetryUserStudySettingView;
import com.old.silence.mcore.dto.PoetryGradeMcoreQuery;
import com.old.silence.mcore.dto.PoetryUserStudySettingRequest;
import com.old.silence.web.bind.annotation.PostJsonMapping;
import com.old.silence.web.bind.annotation.PutJsonMapping;
import com.old.silence.web.data.ProjectedPayloadType;

/**
 * @author moryzang
 */
@FeignClient(name = "silence-content-service", contextId = "poetryUserStudySetting", path = "/api/v1")
public interface PoetryUserStudySettingFeignClient {

    @GetMapping(value = "/poetryUserStudySettings/{subCategoryId}/{gradeId}/{userId}")
    <T> Optional<T> findBySubCategoryIdGradeIdAndUserId(@PathVariable BigInteger subCategoryId, @PathVariable BigInteger gradeId, @PathVariable BigInteger userId,
                                                        @ProjectedPayloadType(PoetryUserStudySettingView.class) Class<T> projectionType);


    @PostJsonMapping("/poetryUserStudySettings")
    BigInteger create(@RequestBody @Validated PoetryUserStudySettingRequest command);

    @PutJsonMapping(value = "/poetryUserStudySettings/{id}")
    void update(@PathVariable BigInteger id, @RequestBody @Validated PoetryUserStudySettingRequest command);
}
