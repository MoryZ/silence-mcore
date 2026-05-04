package com.old.silence.mcore.client.content;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.old.silence.content.api.vo.PoetryUserView;
import com.old.silence.mcore.dto.PoetryUserRequest;
import com.old.silence.web.data.ProjectedPayloadType;

import java.math.BigInteger;
import java.util.Optional;

/**
 * @author moryzang
 */
@FeignClient(name = "silence-content-service", contextId = "poetry-user", path = "/api/v1")
public interface PoetryUserFeignClient {

    @GetMapping("/poetryUsers")
    <T> Optional<T> findByOpenid(@RequestParam String openid, @ProjectedPayloadType(PoetryUserView.class) Class<T> projectionType);

    @GetMapping("/poetryUsers/{id}")
    <T> Optional<T> findById(@PathVariable BigInteger id, @ProjectedPayloadType(PoetryUserView.class) Class<T> projectionType);

    @PostMapping("/poetryUsers")
    BigInteger create(@RequestBody PoetryUserRequest user);

    @PutMapping("/poetryUsers/{id}")
    void update(@PathVariable BigInteger id, @RequestBody PoetryUserRequest user);

    @PutMapping("/poetryUsers/{id}/bindPhone")
    void bindPhone(@PathVariable BigInteger id, @RequestParam String phone);
}
