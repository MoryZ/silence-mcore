package com.old.silence.mcore.vo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

/**
 * @author moryzang
 */
public interface PoetryDailyStudyPlanMcoreView {

    BigInteger getId();

    BigInteger getUserId();

    LocalDate getPlanDate();

    String getNewItemIds();

    String getReviewItemIds();

    String getCompletedNewItems();

    String getCompletedReviewItems();

    BigDecimal getCompletionRate();

    PoetryCategoryMcoreView getPoetryCategory();
}
