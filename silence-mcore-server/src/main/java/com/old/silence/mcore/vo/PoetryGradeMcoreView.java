package com.old.silence.mcore.vo;

import java.math.BigInteger;

import com.old.silence.data.commons.domain.AuditableView;


/**
 * PoetryGrade视图接口
 */
public interface PoetryGradeMcoreView extends AuditableView {
    BigInteger getId();

    String getCode();

    String getName();

    String getDescription();

}