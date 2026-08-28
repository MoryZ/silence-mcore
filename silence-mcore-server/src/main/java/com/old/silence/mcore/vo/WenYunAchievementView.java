package com.old.silence.mcore.vo;

import lombok.Data;

@Data
public class WenYunAchievementView {
    private Integer xp;
    private Integer level;
    private String levelName;
    private String levelIcon;
    private Integer nextLevelXp;
    private String nextLevelName;
    private Double progressToNext;
    private String mascotName;
    private Integer streakDays;
    private Double memoryGraduatedRate;
    private Double wrongMasteredRate;
    private Integer memoryItemCount;
}
