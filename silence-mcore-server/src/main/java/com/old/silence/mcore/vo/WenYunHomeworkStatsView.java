package com.old.silence.mcore.vo;

import lombok.Data;

@Data
public class WenYunHomeworkStatsView {
    private Integer todayPending;
    private Integer openCount;
    private Integer doneCount;
    private Integer overdueCount;
}
