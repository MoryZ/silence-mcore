package com.old.silence.mcore.vo;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class WenYunDashboardTodayView {
    private Integer todoCount;
    private List<Map<String, Object>> topItems;
    private List<Map<String, Object>> collapsed;
    private Map<String, Object> recitation;
    private Map<String, Object> wrongQuestions;
    private Integer streakDays;
}
