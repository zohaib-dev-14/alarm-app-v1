package com.zabisoft.alarm_app.controller;

import com.zabisoft.alarm_app.dto.MathChallengeResponse;
import com.zabisoft.alarm_app.dto.QRChallengeResponse;
import com.zabisoft.alarm_app.service.MathChallengeService;
import com.zabisoft.alarm_app.service.QRChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/challenges")
@RequiredArgsConstructor
public class ChallengeController {

    private final MathChallengeService mathService;
    private final QRChallengeService   qrService;

    @GetMapping("/math/{alarmId}")
    public ResponseEntity<MathChallengeResponse> getMath(
            @PathVariable UUID alarmId) {
        return ResponseEntity.ok(mathService.generate(alarmId));
    }

    @PostMapping("/math/verify")
    public ResponseEntity<Map<String, Boolean>> verifyMath(
            @RequestParam UUID alarmId,
            @RequestParam int answer) {
        return ResponseEntity.ok(
                Map.of("success", mathService.verify(alarmId, answer))
        );
    }

    @GetMapping("/qr/{alarmId}")
    public ResponseEntity<QRChallengeResponse> getQR(
            @PathVariable UUID alarmId) {
        return ResponseEntity.ok(qrService.generate(alarmId));
    }

    @PostMapping("/qr/verify")
    public ResponseEntity<Map<String, Boolean>> verifyQR(
            @RequestParam UUID alarmId,
            @RequestParam String token) {
        return ResponseEntity.ok(
                Map.of("success", qrService.verify(alarmId, token))
        );
    }
}