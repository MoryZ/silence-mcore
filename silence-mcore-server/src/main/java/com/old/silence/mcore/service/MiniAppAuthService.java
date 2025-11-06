package com.old.silence.mcore.service;

import jakarta.validation.Valid;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.old.silence.core.context.CommonErrors;
import com.old.silence.core.exception.ResourceNotFoundException;
import com.old.silence.mcore.client.content.PoetryUserFeignClient;
import com.old.silence.mcore.client.content.PoetryUserLoginLogFeignClient;
import com.old.silence.mcore.dto.BindPhoneRequest;
import com.old.silence.mcore.dto.LoginRequest;
import com.old.silence.mcore.dto.PoetryUserLoginLogRequest;
import com.old.silence.mcore.dto.PoetryUserRequest;
import com.old.silence.mcore.dto.SendVerifyCodeRequest;
import com.old.silence.mcore.dto.VerifyCodeLoginRequest;
import com.old.silence.mcore.security.SilenceHallContextHolder;
import com.old.silence.mcore.security.SilenceHallServerTokenAuthority;
import com.old.silence.mcore.security.SilencePrincipal;
import com.old.silence.mcore.vo.LoginResponse;
import com.old.silence.mcore.vo.PoetryUserMCoreView;
import com.old.silence.mcore.vo.UserResponse;
import com.old.silence.mcore.vo.WxMaLoginResult;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

/**
 * @author moryzang
 */
@Service
public class MiniAppAuthService {

    private static final Logger log = LoggerFactory.getLogger(MiniAppAuthService.class);
    private final WxMaLoginService wxMaLoginService;
    private final PoetryUserFeignClient poetryUserFeignClient;
    private final PoetryUserLoginLogFeignClient poetryUserLoginLogFeignClient;
    private final StringRedisTemplate stringRedisTemplate;
    private final SilenceHallServerTokenAuthority silenceHallServerTokenAuthority;


    public MiniAppAuthService(WxMaLoginService wxMaLoginService,
                              PoetryUserFeignClient poetryUserFeignClient,
                              PoetryUserLoginLogFeignClient poetryUserLoginLogFeignClient,
                              StringRedisTemplate stringRedisTemplate,
                              SilenceHallServerTokenAuthority silenceHallServerTokenAuthority) {
        this.wxMaLoginService = wxMaLoginService;
        this.poetryUserFeignClient = poetryUserFeignClient;
        this.poetryUserLoginLogFeignClient = poetryUserLoginLogFeignClient;
        this.stringRedisTemplate = stringRedisTemplate;
        this.silenceHallServerTokenAuthority = silenceHallServerTokenAuthority;
    }

    /**
     * 小程序登录并返回业务token
     */
    public LoginResponse login(LoginRequest request) {
        try {
            // 1. 根据code获取微信session信息
            WxMaLoginResult wxLoginResult = wxMaLoginService.loginByCode(request.getCode());

            // 2. 处理业务登录逻辑
            return processBusinessLogin(wxLoginResult);

        } catch (WxErrorException e) {
            log.error("微信接口调用失败", e);
            throw new RuntimeException("登录失败: " + e.getError().getErrorMsg());
        } catch (Exception e) {
            log.error("小程序登录异常", e);
            throw new RuntimeException("登录失败");
        }
    }

    public String sendVerifyCode(SendVerifyCodeRequest sendVerifyCodeRequest) {
        return "123456";
    }

    public LoginResponse bindPhoneByVerifyCode(VerifyCodeLoginRequest verifyCodeLoginRequest) {
        // 校验短信验证码
        var loginResponse = new LoginResponse();
        /*var authenticatedUserIdOptional = SilenceHallContextHolder.getAuthenticatedUserId();
        if (authenticatedUserIdOptional.isEmpty()) {
            throw CommonErrors.ACCESS_DENIED.createException();
        }
        var userId = authenticatedUserIdOptional.get();
        poetryUserFeignClient.bindPhone(userId, verifyCodeLoginRequest.getPhone());

        // 查询或创建用户
        Optional<PoetryUserMCoreView> userOptional = poetryUserFeignClient.findById(userId, PoetryUserMCoreView.class);
        PoetryUserMCoreView user = userOptional.orElseGet(() -> createNewUser(openid, unionid));

        // 生成业务token
        SilencePrincipal principal = new SilencePrincipal(user.getId(), unionid);
        String token = silenceHallServerTokenAuthority.issueToken(principal);

        // 记录登录日志
        recordLoginLog(user, openid, unionid, token);

        loginResponse.setToken(loginResult.token());
        loginResponse.setUserInfo(buildUserResponse(loginResult.user()));
        loginResponse.setOpenid(wxLoginResult.getOpenid());*/
        return loginResponse;
    }

    public void bindPhone(BindPhoneRequest request) {
        BigInteger userId;
        String sessionKey;

        // 已登录用户直接绑定
        var authenticatedUserIdOptional = SilenceHallContextHolder.getAuthenticatedUserId();
        if (authenticatedUserIdOptional.isEmpty()) {
            throw CommonErrors.ACCESS_DENIED.createException();
        }
        userId = authenticatedUserIdOptional.get();
        sessionKey = stringRedisTemplate.opsForValue().get("session_key:" + userId);
        if (sessionKey == null) {
            throw CommonErrors.NOT_BLANK.createException("session_key已过期，请重新登录");
        }

        // 解密手机号并绑定
        var wxMaPhoneNumberInfo = wxMaLoginService.decryptPhoneNumber(sessionKey, request.getEncryptedData(), request.getIv());
        poetryUserFeignClient.bindPhone(userId, wxMaPhoneNumberInfo.getPurePhoneNumber());

    }


    /**
     * 处理业务登录逻辑
     */
    private LoginResponse processBusinessLogin(WxMaLoginResult wxLoginResult) {
        var loginResult = performLogin(wxLoginResult);

        var loginResponse = new LoginResponse();
        loginResponse.setToken(loginResult.token());
        loginResponse.setUserInfo(buildUserResponse(loginResult.user()));
        loginResponse.setOpenid(wxLoginResult.getOpenid());
        return loginResponse;
    }

    /**
     * 创建新用户
     */
    private PoetryUserMCoreView createNewUser(String openid, String unionid) {
        PoetryUserRequest user = new PoetryUserRequest();
        user.setOpenid(openid);
        user.setUnionid(unionid);
        var userId = poetryUserFeignClient.create(user);
        return poetryUserFeignClient.findById(userId, PoetryUserMCoreView.class)
                .orElseThrow(ResourceNotFoundException::new);
    }

    /**
     * 构建用户响应信息
     */
    private UserResponse buildUserResponse(PoetryUserMCoreView user) {
        var userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setAvatar(user.getAvatarUrl());
        userResponse.setNickname(user.getNickname());
        userResponse.setPhone(user.getPhone());
        return userResponse;
    }

    /**
     * 执行登录核心逻辑（查询/创建用户、生成token、记录日志、保存sessionKey）
     * @param wxLoginResult 微信登录结果
     * @return 登录结果，包含用户和token
     */
    private LoginResult performLogin(WxMaLoginResult wxLoginResult) {
        String openid = wxLoginResult.getOpenid();
        String unionid = wxLoginResult.getUnionid();

        // 查询或创建用户
        Optional<PoetryUserMCoreView> userOptional = poetryUserFeignClient.findByOpenid(openid, PoetryUserMCoreView.class);
        PoetryUserMCoreView user = userOptional.orElseGet(() -> createNewUser(openid, unionid));

        // 生成业务token
        SilencePrincipal principal = new SilencePrincipal(user.getId(), unionid);
        String token = silenceHallServerTokenAuthority.issueToken(principal);

        // 记录登录日志
        recordLoginLog(user, openid, unionid, token);

        // 将sessionKey存入Redis（用于后续解密手机号）
        saveSessionKeyToRedis(user.getId(), wxLoginResult.getSessionKey());

        return new LoginResult(user, token);
    }

    /**
     * 记录登录日志
     */
    private void recordLoginLog(PoetryUserMCoreView user, String openid, String unionid, String token) {
        PoetryUserLoginLogRequest poetryUserLoginLogCommand = new PoetryUserLoginLogRequest();
        poetryUserLoginLogCommand.setOpenid(openid);
        poetryUserLoginLogCommand.setUnionid(unionid);
        poetryUserLoginLogCommand.setLoginType((byte) 1);
        poetryUserLoginLogCommand.setLoginTime(Instant.now());
        poetryUserLoginLogCommand.setSessionKey(token);
        poetryUserLoginLogCommand.setIpAddress("");
        poetryUserLoginLogCommand.setLoginStatus(true);
        poetryUserLoginLogCommand.setUserId(user.getId());
        poetryUserLoginLogFeignClient.create(poetryUserLoginLogCommand);
    }

    /**
     * 保存sessionKey到Redis
     */
    private void saveSessionKeyToRedis(BigInteger userId, String sessionKey) {
        stringRedisTemplate.opsForValue().set("session_key:" + userId, sessionKey, Duration.ofHours(2));
    }


    /**
         * 登录结果（内部类）
         */
        private record LoginResult(PoetryUserMCoreView user, String token) {

    }
}
