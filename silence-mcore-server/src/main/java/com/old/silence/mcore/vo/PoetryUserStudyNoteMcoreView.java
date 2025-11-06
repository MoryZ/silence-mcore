package com.old.silence.mcore.vo;

import java.math.BigInteger;

/**
 * @author moryzang
 */
public interface PoetryUserStudyNoteMcoreView {

    BigInteger getId();

    BigInteger getUserId();

    BigInteger getContentId();

    String getNoteContent();

    String getTags();

    Boolean getDisclosure();
}
