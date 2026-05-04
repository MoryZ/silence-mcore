package com.old.silence.mcore.vo;

import com.old.silence.content.domain.enums.QuestionType;

import java.math.BigInteger;

/**
 * PoetryQuizQuestions视图接口
 */
public interface PoetryQuizQuestionsMcoreView {
    BigInteger getId();

    BigInteger getContentId();

    QuestionType getQuestionType();

    String getQuestionStem();

    String getQuestionData();

    String getCorrectAnswer();

    String getExplanation();

    Long getDifficulty();

    String getHints();

    Boolean getEnable();


}