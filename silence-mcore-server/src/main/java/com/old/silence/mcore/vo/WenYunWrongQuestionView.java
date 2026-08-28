package com.old.silence.mcore.vo;

import lombok.Data;
import java.math.BigInteger;

@Data
public class WenYunWrongQuestionView {
    private BigInteger id;
    private String subject;
    private String wrongReason;
    private String summary;
    private String correctAnswer;
    private String note;
    private Integer reviewStage;
    private Boolean graduated;
    private String nextReviewAt;
    private String lastReviewAt;
    private Integer reviewCount;
    private Double mastery;
    private WenYunReviewStats stats;
    private String createdAt;
}
