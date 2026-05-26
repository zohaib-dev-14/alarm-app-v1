package com.zabisoft.alarm_app.entities;
import com.zabisoft.alarm_app.enums.ChallengeType;
import com.zabisoft.alarm_app.enums.Difficulty;
import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "alarms")
//@Data // claude gave an unsafe code
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Alarm extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String label;
    private LocalTime alarmTime;
    private boolean isActive;

    @Enumerated(EnumType.STRING)
    private ChallengeType challengeType;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    private Set<DayOfWeek> repeatDays;
    private boolean snoozeable;
    private int snoozeMinutes;
}