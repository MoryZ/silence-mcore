package com.old.silence.mcore.vo;

import lombok.Data;
import java.math.BigInteger;

@Data
public class WenYunRecitationView {
    private BigInteger id;
    private String subject;
    private String title;
    private String author;
    private String category;
    private String content;
    private BigInteger itemId;
    private Integer reviewStage;
    private Boolean graduated;
    private String nextReviewAt;
    private String lastReviewAt;
    private Integer reviewCount;
    private Double mastery;
    private WenYunReviewStats stats;
    private String createdAt;
}
