package com.old.silence.mcore.vo;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class WenYunRecitationStatsView {
    private Integer todayDue;
    private Integer learning;
    private Integer graduated;
    private List<Map<String, Object>> byCategory;
}
