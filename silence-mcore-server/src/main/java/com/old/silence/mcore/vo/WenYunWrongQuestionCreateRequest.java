package com.old.silence.mcore.vo;

import lombok.Data;
import java.math.BigInteger;

@Data
public class WenYunWrongQuestionCreateRequest {
    private BigInteger userId;
    private String subject;
    private String wrongReason;
    private String summary;
    private String correctAnswer;
    private String note;
}
