package com.old.silence.mcore.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.old.silence.content.domain.enums.ContentReferenceMode;
import com.old.silence.content.domain.enums.ContentStatus;
import com.old.silence.content.domain.enums.ContentType;
import com.old.silence.content.domain.enums.CoverImageReferenceMode;

import java.math.BigInteger;
import java.time.Instant;
import java.util.Map;

/**
 * @author moryzang
 */
public interface ContentMcoreView {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    BigInteger getId();

    String getTitle();

    ContentStatus getStatus();

    ContentType getType();

    String getAuthor();

    String getCoverImageReference();

    CoverImageReferenceMode getCoverImageReferenceMode();

    String getContentCode();

    Instant getPublishedAt();

    ContentReferenceMode getContentReferenceMode();

    String getContentReference();

    String getKeywords();

    Boolean getStickyTop();

    Instant getStickyTopAt();

    BigInteger getParentId();

    BigInteger getRootId();

    Map<String, Object> getAttributes();

    Instant getExpiredAt();
}
