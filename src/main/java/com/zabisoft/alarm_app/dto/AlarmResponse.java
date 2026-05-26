package com.zabisoft.alarm_app.dto;

import com.zabisoft.alarm_app.enums.ChallengeType;
import com.zabisoft.alarm_app.enums.Difficulty;
import lombok.Builder;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class AlarmResponse {
    private UUID id;
    private String label;
    private String alarmTime;
    private boolean isActive;
    private ChallengeType challengeType;
    private Difficulty difficulty;
    private Set<DayOfWeek> repeatDays;
    private boolean snoozeable;
    private int snoozeMinutes;
}