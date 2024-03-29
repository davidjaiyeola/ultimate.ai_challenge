package com.ultimate.ai.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntentApiRequestDto {
    private String botId;
    private String message;
}
