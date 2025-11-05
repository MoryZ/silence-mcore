package com.old.silence.mcore.api.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaUserService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author moryzang
 */
@Configuration
@ConfigurationProperties(prefix = "wechat.miniapp")
public class WxMaConfiguration {

    private String appId;
    private String secret;
    private Long sessionKeyTimeout;

    @Bean
    public WxMaService wxMaService() {
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid(appId);
        config.setSecret(secret);

        WxMaService wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(config);
        return wxMaService;
    }

    @Bean
    public WxMaUserService wxMaUserService(WxMaService wxMaService) {
        return wxMaService.getUserService();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getSessionKeyTimeout() {
        return sessionKeyTimeout;
    }

    public void setSessionKeyTimeout(Long sessionKeyTimeout) {
        this.sessionKeyTimeout = sessionKeyTimeout;
    }
}