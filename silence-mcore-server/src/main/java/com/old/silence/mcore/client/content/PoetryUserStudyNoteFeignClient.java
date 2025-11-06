package com.old.silence.mcore.client.content;

import java.math.BigInteger;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.old.silence.content.api.vo.PoetryUserStudyNoteView;
import com.old.silence.mcore.dto.PoetryUserStudyNoteRequest;
import com.old.silence.web.bind.annotation.PostJsonMapping;
import com.old.silence.web.bind.annotation.PutJsonMapping;
import com.old.silence.web.data.ProjectedPayloadType;

/**
 * @author moryzang
 */

@FeignClient(name = "silence-content-service", contextId = "poetryUserStudyNote", path = "/api/v1")
public interface PoetryUserStudyNoteFeignClient {

    @GetMapping(value = "/poetryUserStudyNotes/{contentId}/{userId}")
    <T> List<T> findByContentIdAndUserId(@PathVariable BigInteger contentId, @PathVariable BigInteger userId,
                                         @ProjectedPayloadType(PoetryUserStudyNoteView.class) Class<T> projectionType);


    @PostJsonMapping("/poetryUserStudyNotes")
    BigInteger create(@RequestBody @Validated PoetryUserStudyNoteRequest command);

    @PutJsonMapping(value = "/poetryUserStudyNotes/{id}")
    void update(@PathVariable BigInteger id, @RequestBody @Validated PoetryUserStudyNoteRequest command);

    @DeleteMapping("/poetryUserStudyNotes/{id}")
    void deleteById(@PathVariable BigInteger id);
}
