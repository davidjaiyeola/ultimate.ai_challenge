package com.ultimate.ai.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IntentApiResponseDto {
    private Double confidence;
    private String intent;
}
