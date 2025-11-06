package com.old.silence.mcore.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

import org.springframework.data.repository.query.parser.Part;
import com.old.silence.content.domain.enums.QuestionType;
import com.old.silence.data.commons.annotation.RelationalQueryProperty;

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