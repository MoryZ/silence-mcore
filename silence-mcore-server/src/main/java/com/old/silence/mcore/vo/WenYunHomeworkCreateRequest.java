package com.old.silence.mcore.vo;

import lombok.Data;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
public class WenYunHomeworkCreateRequest {
    private BigInteger userId;
    private String subject;
    private String title;
    private LocalDate submitDate;
    private String note;
}
