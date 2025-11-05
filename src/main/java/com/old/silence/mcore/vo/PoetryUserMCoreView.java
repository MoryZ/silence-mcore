package com.old.silence.mcore.vo;

import java.math.BigInteger;

import com.old.silence.content.domain.enums.GradeLevel;

/**
 * @author moryzang
 */
public interface PoetryUserMCoreView {

    BigInteger getId();

    String getOpenid();

    String getNickname();

    String getAvatarUrl();

    String getPhone();

    GradeLevel getGradeLevel();

    Long getStudyGoalDaily();
}
