package com.ultimate.ai.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IntentApiRequestDto {
    private String botId;
    private String message;
}
