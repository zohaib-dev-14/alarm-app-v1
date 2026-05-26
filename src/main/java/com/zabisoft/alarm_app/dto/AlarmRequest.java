package com.zabisoft.alarm_app.dto;

import com.zabisoft.alarm_app.enums.ChallengeType;
import com.zabisoft.alarm_app.enums.Difficulty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.DayOfWeek;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class AlarmRequest {
    @NotBlank private String label;
    @NotBlank private String alarmTime;
    private boolean isActive;
    @NotNull private ChallengeType challengeType;
    @NotNull private Difficulty difficulty;
    private Set<DayOfWeek> repeatDays;
    private boolean snoozeable;
    private int snoozeMinutes;
}