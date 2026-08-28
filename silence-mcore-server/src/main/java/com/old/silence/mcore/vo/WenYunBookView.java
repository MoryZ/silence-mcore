package com.old.silence.mcore.vo;

import lombok.Data;
import java.math.BigInteger;

@Data
public class WenYunBookView {
    private BigInteger id;
    private String name;
    private String shortName;
    private String subject;
    private String category;
    private Integer totalCount;
    private String cover;
}
