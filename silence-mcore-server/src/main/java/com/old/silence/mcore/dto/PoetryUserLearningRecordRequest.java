package com.old.silence.mcore.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;

/**
 * PoetryUserLearningRecord命令对象
 */
@Setter
@Getter
public class PoetryUserLearningRecordRequest {
    private BigInteger userId;

    @NotNull
    private BigInteger subCategoryId;

    @NotNull
    private BigInteger contentId;

    @NotNull
    private Instant firstStudiedAt;
    private Instant lastReviewedAt;
    @NotNull
    private Instant nextReviewAt;
    private Long reviewCount;
    private BigDecimal memoryStrength;
    private BigDecimal easinessFactor;
    private Long learningPhase;
    private Boolean remembered;
    private Long studyDuration;

}