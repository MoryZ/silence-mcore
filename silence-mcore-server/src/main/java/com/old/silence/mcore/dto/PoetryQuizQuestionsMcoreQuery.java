package com.old.silence.mcore.dto;

import lombok.Getter;
import lombok.Setter;
import com.old.silence.content.domain.enums.QuestionType;

import java.math.BigInteger;

/**
 * PoetryQuizQuestions查询对象
 */
@Setter
@Getter
public class PoetryQuizQuestionsMcoreQuery {
    private BigInteger contentId;
    private QuestionType questionType;
    private Long difficulty;


}