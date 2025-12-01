package com.old.silence.mcore.dto;

import lombok.Data;
import com.old.silence.content.domain.enums.ContentStatus;
import com.old.silence.content.domain.enums.ContentType;

import java.time.Instant;

/**
 * @author moryzang
 */
@Data
public class ContentMcoreQuery {

    private ContentType type;

    private ContentStatus status;

    private Instant publishedAtStart;

    private Instant publishedAtEnd;

}
