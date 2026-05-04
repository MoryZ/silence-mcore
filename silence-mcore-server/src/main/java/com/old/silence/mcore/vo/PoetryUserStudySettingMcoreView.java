package com.old.silence.mcore.vo;

import com.old.silence.content.domain.enums.StudyMode;
import com.old.silence.content.domain.enums.StudyStatus;

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

    Long getTotalCount();

    Long getDailyNewCount();

    Long getDailyReviewCount();

    StudyMode getStudyMode();

    StudyStatus getStatus();

    LocalTime getStudyReminderTime();

    Boolean getEnableDarkMode();

    Long getStudySessionMinutes();
}
