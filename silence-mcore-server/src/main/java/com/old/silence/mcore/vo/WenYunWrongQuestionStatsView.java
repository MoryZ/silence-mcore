package com.old.silence.mcore.vo;

import lombok.Data;
import java.util.Map;

@Data
public class WenYunWrongQuestionStatsView {
    private Integer todayDue;
    private Integer total;
    private Integer graduated;
    private Map<String, Integer> byReason;
}
