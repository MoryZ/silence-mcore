package com.old.silence.mcore.api;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.old.silence.mcore.dto.BindPhoneRequest;
import com.old.silence.mcore.dto.LoginRequest;
import com.old.silence.mcore.dto.SendVerifyCodeRequest;
import com.old.silence.mcore.dto.VerifyCodeLoginRequest;
import com.old.silence.mcore.result.ApiResult;
import com.old.silence.mcore.service.MiniAppAuthService;
import com.old.silence.mcore.vo.LoginResponse;

/**
 * @author moryzang
 */
@RestController
@RequestMapping("/api/v1")
@Validated
public class MiniAppAuthResource {


    private static final Logger log = LoggerFactory.getLogger(MiniAppAuthResource.class);
    private final MiniAppAuthService miniAppAuthService;

    public MiniAppAuthResource(MiniAppAuthService miniAppAuthService) {
        this.miniAppAuthService = miniAppAuthService;
    }

    /**
     * 小程序登录接口
     */
    @PostMapping("/auth/login")
    public ApiResult<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        log.info("小程序登录请求授权码: {}", request.getCode());
        return ApiResult.success(miniAppAuthService.login(request));
    }

    /**
     * 绑定手机号
     */
    @PostMapping("/auth/bindPhone")
    public ApiResult<Boolean> bindPhone(@Valid @RequestBody BindPhoneRequest request) {
        miniAppAuthService.bindPhone(request);
        return ApiResult.success(true);
    }

    /**
     * 绑定手机号
     */
    @PostMapping("/auth/verifyCodeLogin")
    public ApiResult<LoginResponse> verifyCodeLogin(@Valid @RequestBody VerifyCodeLoginRequest verifyCodeLoginRequest) {
        return ApiResult.success(miniAppAuthService.bindPhoneByVerifyCode(verifyCodeLoginRequest));
    }

    @PostMapping("/auth/sendVerifyCode")
    public ApiResult<String> sendVerifyCode(@RequestBody SendVerifyCodeRequest sendVerifyCodeRequest) {
        return ApiResult.success(miniAppAuthService.sendVerifyCode(sendVerifyCodeRequest));
    }
}