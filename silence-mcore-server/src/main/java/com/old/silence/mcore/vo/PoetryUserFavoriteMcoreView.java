package com.old.silence.mcore.vo;

import com.old.silence.data.commons.domain.AuditableView;

import java.math.BigInteger;

/**
 * PoetryUserFavorite视图接口
 */
public interface PoetryUserFavoriteMcoreView extends AuditableView {
    BigInteger getId();

    BigInteger getUserId();

    BigInteger getContentId();

}