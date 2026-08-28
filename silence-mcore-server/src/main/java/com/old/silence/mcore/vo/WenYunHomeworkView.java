package com.old.silence.mcore.vo;

import lombok.Data;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
public class WenYunHomeworkView {
    private BigInteger id;
    private String subject;
    private String title;
    private LocalDate submitDate;
    private String note;
    private String status;
    private String completedAt;
    private String projectId;
    private String createdAt;
}
