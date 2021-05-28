package com.ultimate.ai.challenge.service;

import com.ultimate.ai.challenge.dto.IntentApiRequestDto;
import com.ultimate.ai.challenge.dto.IntentApiResponseDto;

import java.util.List;

public interface UltimateAIRestService {
    List<IntentApiResponseDto> processHttpPost(IntentApiRequestDto request, String path);
}
