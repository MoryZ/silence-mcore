package com.old.silence.mcore.vo;

import java.math.BigInteger;
import java.time.LocalTime;

/**
 * @author moryzang
 */
public interface PoetryUserStudySettingMcoreView {

    BigInteger getId();

    BigInteger getUserId();

    BigInteger getGradeId();

    BigInteger getSubCategoryId();

    Long getDailyNewItems();

    Long getDailyReviewItems();

    LocalTime getStudyReminderTime();

    Boolean getEnableDarkMode();

    Long getStudySessionMinutes();
}
