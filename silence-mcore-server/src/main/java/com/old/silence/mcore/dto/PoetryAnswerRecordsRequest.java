package com.old.silence.mcore.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Map;

/**
* PoetryAnswerRecords命令对象
*/
@Setter
@Getter
public class PoetryAnswerRecordsRequest {
    private BigInteger userId;
    @NotNull
    private BigInteger quizId;
    @NotNull
    private BigInteger contentId;
    @NotNull
    private BigInteger subCategoryId;
    @NotNull
    private Map<String, Object> userAnswer;
    @NotNull
    private Boolean correct;
    @NotNull
    private Long hintsUsed;
    private String sessionId;
    private Long responseTime;

}