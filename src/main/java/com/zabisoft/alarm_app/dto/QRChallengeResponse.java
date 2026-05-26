// QRChallengeResponse.java
package com.zabisoft.alarm_app.dto;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QRChallengeResponse {
    private UUID alarmId;
    private String token;
}