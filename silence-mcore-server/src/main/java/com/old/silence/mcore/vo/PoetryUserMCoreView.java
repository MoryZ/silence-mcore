package com.old.silence.mcore.vo;

import com.old.silence.content.domain.enums.GradeLevel;

import java.math.BigInteger;

/**
 * @author moryzang
 */
public interface PoetryUserMCoreView {

    BigInteger getId();

    String getOpenid();

    String getNickname();

    String getAvatarUrl();

    String getPhone();

    String getGender();

    String getBirthday();

    String getAddress();

    GradeLevel getGradeLevel();

    Long getStudyGoalDaily();
}
