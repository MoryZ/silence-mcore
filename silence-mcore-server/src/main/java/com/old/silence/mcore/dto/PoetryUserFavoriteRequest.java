package com.old.silence.mcore.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * PoetryUserFavorite命令对象
 */
@Setter
@Getter
public class PoetryUserFavoriteRequest {
    private BigInteger userId;
    @NotNull
    private BigInteger contentId;

}