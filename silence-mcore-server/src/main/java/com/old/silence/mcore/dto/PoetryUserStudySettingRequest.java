package com.old.silence.mcore.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigInteger;
import java.time.LocalTime;

/**
 * @author moryzang
 */
public class PoetryUserStudySettingRequest {

    private BigInteger userId;
    @NotNull
    private BigInteger gradeId;
    @NotNull
    private BigInteger subCategoryId;
    private Long dailyNewItems;
    private Long dailyReviewItems;
    private LocalTime studyReminderTime;
    private Boolean enableDarkMode;
    private Long studySessionMinutes;

    public BigInteger getUserId() {
        return this.userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public BigInteger getGradeId() {
        return gradeId;
    }

    public void setGradeId(BigInteger gradeId) {
        this.gradeId = gradeId;
    }

    public BigInteger getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(BigInteger subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public Long getDailyNewItems() {
        return this.dailyNewItems;
    }

    public void setDailyNewItems(Long dailyNewItems) {
        this.dailyNewItems = dailyNewItems;
    }

    public Long getDailyReviewItems() {
        return this.dailyReviewItems;
    }

    public void setDailyReviewItems(Long dailyReviewItems) {
        this.dailyReviewItems = dailyReviewItems;
    }

    public LocalTime getStudyReminderTime() {
        return this.studyReminderTime;
    }

    public void setStudyReminderTime(LocalTime studyReminderTime) {
        this.studyReminderTime = studyReminderTime;
    }

    public Boolean getEnableDarkMode() {
        return this.enableDarkMode;
    }

    public void setEnableDarkMode(Boolean enableDarkMode) {
        this.enableDarkMode = enableDarkMode;
    }

    public Long getStudySessionMinutes() {
        return this.studySessionMinutes;
    }

    public void setStudySessionMinutes(Long studySessionMinutes) {
        this.studySessionMinutes = studySessionMinutes;
    }
}
