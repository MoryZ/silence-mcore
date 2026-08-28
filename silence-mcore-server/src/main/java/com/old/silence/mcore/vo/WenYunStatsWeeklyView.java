package com.old.silence.mcore.vo;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class WenYunStatsWeeklyView {
    private Map<String, String> period;
    private Map<String, Object> summary;
    private List<Map<String, Object>> dailyChart;
}
