package com.ultimate.ai.challenge.service;

import com.ultimate.ai.challenge.dto.IntentApiRequestDto;
import com.ultimate.ai.challenge.dto.IntentApiResponseDto;

public interface UltimateAIRestService {
    IntentApiResponseDto processHttpPost(IntentApiRequestDto request, String path);
}
