package com.old.silence.mcore.vo;

import com.old.silence.data.commons.domain.AuditableView;

import java.math.BigInteger;
import java.util.Map;

/**
 * PoetryAnswerRecords视图接口
 */
public interface PoetryAnswerRecordsMcoreView extends AuditableView {
    BigInteger getId();

    BigInteger getUserId();

    BigInteger getQuizId();

    BigInteger getSubCategoryId();

    BigInteger getContentId();

    Map<String, Object> getUserAnswer();

    Boolean getCorrect();

    Long getHintsUsed();

    String getSessionId();

    Long getResponseTime();


}