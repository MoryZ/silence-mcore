package com.old.silence.mcore.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author moryzang
 */
@Getter
@Setter
public class PoetryCategoryMcoreQuery {

    private String name;
    private String code;
    private String icon;
    private Long sortOrder;
    private BigInteger parentId;
    private BigInteger gradeId;
}
