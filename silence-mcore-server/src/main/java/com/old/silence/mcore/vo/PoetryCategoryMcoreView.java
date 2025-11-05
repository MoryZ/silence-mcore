package com.old.silence.mcore.vo;

import com.old.silence.data.commons.domain.AuditableView;

import java.math.BigInteger;


/**
 * PoetryGrade视图接口
 */
public interface PoetryCategoryMcoreView extends AuditableView {
    BigInteger getId();

    String getName();

    String getCode();

    String getIcon();

    Long getSortOrder();

    BigInteger getParentId();

}