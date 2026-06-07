package com.old.silence.mcore.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * UserInteractionLog查询对象
 */
@Setter
@Getter
public class ContentUserInteractionLogMcoreQuery {
    private BigInteger userId;
    private BigInteger resourceId;


}