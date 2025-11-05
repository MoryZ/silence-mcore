package com.old.silence.mcore.service;

import cn.binarywang.wx.miniapp.api.WxMaUserService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.old.silence.mcore.vo.WxMaLoginResult;

/**
 * @author moryzang
 */
@Service
public class WxMaLoginService {


    private static final Logger log = LoggerFactory.getLogger(WxMaLoginService.class);

    private final WxMaUserService wxMaUserService;

    public WxMaLoginService(WxMaUserService wxMaUserService) {
        this.wxMaUserService = wxMaUserService;
    }

    /**
     * 根据code登录
     */
    public WxMaLoginResult loginByCode(String code) throws WxErrorException {
        log.info("小程序登录，code: {}", code);

        // 调用微信接口获取session信息
        WxMaJscode2SessionResult sessionInfo = wxMaUserService.getSessionInfo(code);

        // 构建登录结果
        WxMaLoginResult loginResult = new WxMaLoginResult();
        loginResult.setOpenid(sessionInfo.getOpenid());
        loginResult.setSessionKey(sessionInfo.getSessionKey());
        loginResult.setUnionid(sessionInfo.getUnionid());

        log.info("小程序登录成功，openid: {}", sessionInfo.getOpenid());
        return loginResult;
    }

    /**
     * 校验用户信息完整性
     */
    public boolean checkUserInfo(String sessionKey, String rawData, String signature) {
        return wxMaUserService.checkUserInfo(sessionKey, rawData, signature);
    }

    /**
     * 解密用户信息
     */
    public WxMaUserInfo decryptUserInfo(String sessionKey, String encryptedData, String iv) {
        try {
            return wxMaUserService.getUserInfo(sessionKey, encryptedData, iv);
        } catch (Exception e) {
            log.error("解密用户信息失败", e);
            throw new RuntimeException("解密用户信息失败");
        }
    }

    /**
     * 解密手机号
     */
    public WxMaPhoneNumberInfo decryptPhoneNumber(String sessionKey, String encryptedData, String iv) {
        try {
            return wxMaUserService.getPhoneNoInfo(sessionKey, encryptedData, iv);
        } catch (Exception e) {
            log.error("解密手机号失败", e);
            throw new RuntimeException("解密手机号失败");
        }
    }
}