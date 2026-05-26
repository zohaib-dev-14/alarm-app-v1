package com.zabisoft.alarm_app.controller;

import com.zabisoft.alarm_app.dto.AlarmRequest;
import com.zabisoft.alarm_app.dto.AlarmResponse;
import com.zabisoft.alarm_app.service.AlarmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/alarms")
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;

    @PostMapping
    public ResponseEntity<AlarmResponse> create(
            @Valid @RequestBody AlarmRequest request) {
        return ResponseEntity.status(201)
                .body(alarmService.createAlarm(request));
    }
    @GetMapping("/test")
    public String test() {

        var auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        System.out.println(auth);
        System.out.println(auth.getAuthorities());

        return "OK";
    }

    @GetMapping
    public ResponseEntity<List<AlarmResponse>> getAll() {
        return ResponseEntity.ok(alarmService.getAlarms());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlarmResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody AlarmRequest request) {
        return ResponseEntity.ok(alarmService.updateAlarm(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        alarmService.deleteAlarm(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<AlarmResponse> toggle(
            @PathVariable UUID id,
            @RequestParam boolean isActive) {
        return ResponseEntity.ok(alarmService.toggleAlarm(id, isActive));
    }
}