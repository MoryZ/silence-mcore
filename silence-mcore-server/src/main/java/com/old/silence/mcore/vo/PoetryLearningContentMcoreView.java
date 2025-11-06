package com.old.silence.mcore.vo;

import java.math.BigInteger;

/**
 * @author moryzang
 */
public interface PoetryLearningContentMcoreView {
    BigInteger getId();

    String getTitle();

    String getSubtitle();

    Long getContentType();

    BigInteger getGradeId();

    BigInteger getCategoryId();

    BigInteger getSubCategoryId();

    Long getDifficulty();

    String getOriginalText();

    String getAuthor();

    String getDynasty();

    String getBackground();

    String getExplanation();

    String getUsageExamples();

    String getAnnotations();

    String getTranslation();

    String getAppreciation();

    String getAudioUrl();

    String getImageUrl();

    Long getViewCount();

    Boolean getEnabled();
}
