package com.old.silence.mcore.vo;

import lombok.Data;
import java.math.BigInteger;

@Data
public class WenYunRecitationCreateRequest {
    private BigInteger userId;
    private String subject;
    private String title;
    private String author;
    private String category;
    private String content;
    private BigInteger itemId;
}
