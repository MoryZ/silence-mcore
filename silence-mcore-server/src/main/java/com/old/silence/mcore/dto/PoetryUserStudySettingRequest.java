package com.old.silence.mcore.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import com.old.silence.content.domain.enums.StudyMode;

import java.math.BigInteger;
import java.time.LocalTime;

/**
 * @author moryzang
 */
@Setter
@Getter
public class PoetryUserStudySettingRequest {

    private BigInteger userId;
    @NotNull
    private BigInteger gradeId;
    @NotNull
    private BigInteger subCategoryId;

    private Long totalCount;
    private Long dailyNewCount;
    private Long dailyReviewCount;
    private StudyMode studyMode;
    private LocalTime studyReminderTime;
    private Boolean enableDarkMode;
    private Long studySessionMinutes;

}
