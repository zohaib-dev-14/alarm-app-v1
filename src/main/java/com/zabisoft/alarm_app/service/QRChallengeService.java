package com.zabisoft.alarm_app.service;

import com.zabisoft.alarm_app.dto.QRChallengeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QRChallengeService {

    private final StringRedisTemplate redisTemplate;

    public QRChallengeResponse generate(UUID alarmId) {
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(
                "qr:alarm:" + alarmId,
                token,
                Duration.ofMinutes(10)
        );
        return QRChallengeResponse.builder()
                .alarmId(alarmId)
                .token(token)
                .build();
    }

    public boolean verify(UUID alarmId, String scannedToken) {
        String stored = redisTemplate.opsForValue()
                .get("qr:alarm:" + alarmId);
        if (stored == null) throw new RuntimeException("QR expired");

        boolean valid = stored.equals(scannedToken);
        if (valid) redisTemplate.delete("qr:alarm:" + alarmId);
        return valid;
    }
}