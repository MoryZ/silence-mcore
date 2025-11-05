package com.old.silence.mcore.client.content;

import java.math.BigInteger;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.old.silence.mcore.dto.PoetryUserLoginLogRequest;

/**
 * @author moryzang
 */
@FeignClient(name = "silence-content-service", contextId = "poetry-user-login-log", path = "/api/v1")
public interface PoetryUserLoginLogFeignClient {

    @PostMapping("/poetryUserLoginLogs")
    BigInteger create(@RequestBody PoetryUserLoginLogRequest poetryUserLoginLog);
}
