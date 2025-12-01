package com.old.silence.mcore.service;

import org.springframework.stereotype.Service;

import com.old.silence.mcore.client.content.PoetryUserFeignClient;
import com.old.silence.mcore.service.view.BigIdOnlyView;

import java.math.BigInteger;

/**
 * @author moryzang
 */
@Service
public class UserService {

    private final PoetryUserFeignClient poetryUserFeignClient;

    public UserService(PoetryUserFeignClient poetryUserFeignClient) {
        this.poetryUserFeignClient = poetryUserFeignClient;
    }


    public boolean existsByUserId(BigInteger userId) {
        return poetryUserFeignClient.findById(userId, BigIdOnlyView.class).isPresent();
    }
}
