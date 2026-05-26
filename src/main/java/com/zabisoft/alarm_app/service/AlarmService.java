package com.zabisoft.alarm_app.service;

import com.zabisoft.alarm_app.dto.AlarmRequest;
import com.zabisoft.alarm_app.dto.AlarmResponse;
import com.zabisoft.alarm_app.entities.Alarm;
import com.zabisoft.alarm_app.entities.User;
import com.zabisoft.alarm_app.helper.AuthHelper;
import com.zabisoft.alarm_app.repositories.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmRepository alarmRepository;
    private final AuthHelper authHelper;

    // CREATE
    public AlarmResponse createAlarm(AlarmRequest request) {

        User user = authHelper.getCurrentUser();

        Alarm alarm = Alarm.builder()
                .user(user)
                .label(request.getLabel())
                .alarmTime(LocalTime.parse(request.getAlarmTime()))
                .isActive(request.isActive())
                .challengeType(request.getChallengeType())
                .difficulty(request.getDifficulty())
                .repeatDays(request.getRepeatDays())
                .snoozeable(request.isSnoozeable())
                .snoozeMinutes(request.getSnoozeMinutes())
                .build();

        return mapToResponse(
                alarmRepository.save(alarm)
        );
    }

    // GET ALL
    public List<AlarmResponse> getAlarms() {

        User user = authHelper.getCurrentUser();

        return alarmRepository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // UPDATE
    public AlarmResponse updateAlarm(
            UUID id,
            AlarmRequest request
    ) {

        Alarm alarm = getAlarmOfCurrentUser(id);

        alarm.setLabel(request.getLabel());

        alarm.setAlarmTime(
                LocalTime.parse(request.getAlarmTime())
        );

        alarm.setActive(request.isActive());

        alarm.setChallengeType(
                request.getChallengeType()
        );

        alarm.setDifficulty(
                request.getDifficulty()
        );

        alarm.setRepeatDays(
                request.getRepeatDays()
        );

        alarm.setSnoozeable(
                request.isSnoozeable()
        );

        alarm.setSnoozeMinutes(
                request.getSnoozeMinutes()
        );

        return mapToResponse(
                alarmRepository.save(alarm)
        );
    }

    // DELETE
    public void deleteAlarm(UUID id) {

        Alarm alarm = getAlarmOfCurrentUser(id);

        alarmRepository.delete(alarm);
    }

    // TOGGLE
    public AlarmResponse toggleAlarm(
            UUID id,
            boolean isActive
    ) {

        Alarm alarm = getAlarmOfCurrentUser(id);

        alarm.setActive(isActive);

        return mapToResponse(
                alarmRepository.save(alarm)
        );
    }

    // OWNERSHIP CHECK
    private Alarm getAlarmOfCurrentUser(UUID id) {

        User user = authHelper.getCurrentUser();

        return alarmRepository.findByIdAndUser(id, user)
                .orElseThrow(
                        () -> new RuntimeException("Alarm not found")
                );
    }

    // ENTITY -> RESPONSE DTO
    private AlarmResponse mapToResponse(Alarm alarm) {

        return AlarmResponse.builder()
                .id(alarm.getId())
                .label(alarm.getLabel())

                // LocalTime -> String
                .alarmTime(alarm.getAlarmTime().toString())

                .isActive(alarm.isActive())
                .challengeType(alarm.getChallengeType())
                .difficulty(alarm.getDifficulty())
                .repeatDays(alarm.getRepeatDays())
                .snoozeable(alarm.isSnoozeable())
                .snoozeMinutes(alarm.getSnoozeMinutes())
                .build();
    }
}