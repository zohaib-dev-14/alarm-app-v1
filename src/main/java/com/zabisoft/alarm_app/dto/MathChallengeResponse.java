// MathChallengeResponse.java
package com.zabisoft.alarm_app.dto;

import lombok.*;

import java.util.UUID;

@Setter
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MathChallengeResponse {
    private UUID alarmId;
    private String question;
}