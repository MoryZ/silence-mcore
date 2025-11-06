package com.old.silence.mcore.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

import org.springframework.data.repository.query.parser.Part;
import com.old.silence.data.commons.annotation.RelationalQueryProperty;

/**
 * PoetryUserFavorite查询对象
 */
@Setter
@Getter
public class PoetryUserFavoriteMcoreQuery {
    private BigInteger userId;
    private BigInteger contentId;


}