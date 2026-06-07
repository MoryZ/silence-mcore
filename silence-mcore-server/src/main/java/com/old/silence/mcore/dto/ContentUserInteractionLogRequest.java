package com.old.silence.mcore.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import com.old.silence.content.domain.enums.InteractionType;
import com.old.silence.content.domain.enums.ResourceType;

import java.math.BigInteger;

/**
 * UserInteractionLog命令对象
 */
@Setter
@Getter
public class ContentUserInteractionLogRequest {
    private BigInteger userId;
    @NotNull
    private BigInteger resourceId;
    @NotNull
    private ResourceType resourceType;
    @NotNull
    private InteractionType interactionType;

}