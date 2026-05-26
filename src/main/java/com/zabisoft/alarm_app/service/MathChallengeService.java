package com.zabisoft.alarm_app.service;

import com.zabisoft.alarm_app.dto.MathChallengeResponse;
import com.zabisoft.alarm_app.entities.Alarm;
import com.zabisoft.alarm_app.repositories.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MathChallengeService {

    private final StringRedisTemplate redisTemplate;
    private final AlarmRepository alarmRepository;
    private final Random random = new Random();

    public MathChallengeResponse generate(UUID alarmId) {
        Alarm alarm = alarmRepository.findById(alarmId)
                .orElseThrow(() -> new RuntimeException("Alarm not found"));

        int[] nums      = getNumbers(alarm.getDifficulty().name());
        String op       = getOperator(alarm.getDifficulty().name());
        int answer      = calculate(nums[0], nums[1], op);
        String question = nums[0] + " " + op + " " + nums[1] + " = ?";

        redisTemplate.opsForValue().set(
                "math:alarm:" + alarmId,
                String.valueOf(answer),
                Duration.ofMinutes(5)
        );

        return MathChallengeResponse.builder()
                .alarmId(alarmId)
                .question(question)
                .build();
    }

    public boolean verify(UUID alarmId, int userAnswer) {
        String stored = redisTemplate.opsForValue()
                .get("math:alarm:" + alarmId);
        if (stored == null) throw new RuntimeException("Challenge expired");

        boolean correct = Integer.parseInt(stored) == userAnswer;
        if (correct) redisTemplate.delete("math:alarm:" + alarmId);
        return correct;
    }

    private int[] getNumbers(String difficulty) {
        return switch (difficulty) {
            case "EASY"   -> new int[]{random.nextInt(20) + 1,  random.nextInt(20) + 1};
            case "MEDIUM" -> new int[]{random.nextInt(50) + 10, random.nextInt(50) + 10};
            case "HARD"   -> new int[]{random.nextInt(100) + 20, random.nextInt(100) + 20};
            default       -> new int[]{random.nextInt(10) + 1,  random.nextInt(10) + 1};
        };
    }

    private String getOperator(String difficulty) {
        String[] easy   = {"+", "-"};
        String[] medium = {"+", "-", "*"};
        String[] hard   = {"*", "-", "+"};
        String[] ops    = difficulty.equals("EASY") ? easy :
                difficulty.equals("MEDIUM") ? medium : hard;
        return ops[random.nextInt(ops.length)];
    }

    private int calculate(int a, int b, String op) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default  -> a + b;
        };
    }
}