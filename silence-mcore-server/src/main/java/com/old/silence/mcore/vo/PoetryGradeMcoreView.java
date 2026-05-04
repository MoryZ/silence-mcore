package com.old.silence.mcore.vo;

import com.old.silence.data.commons.domain.AuditableView;

import java.math.BigInteger;


/**
 * PoetryGrade视图接口
 */
public interface PoetryGradeMcoreView extends AuditableView {
    BigInteger getId();

    String getCode();

    String getName();

    String getDescription();

}