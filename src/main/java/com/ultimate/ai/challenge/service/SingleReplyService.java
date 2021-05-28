package com.ultimate.ai.challenge.service;

import com.ultimate.ai.challenge.dto.IntentApiRequestDto;
import com.ultimate.ai.challenge.model.SingleReply;
import reactor.core.publisher.Mono;

public interface SingleReplyService {
    Mono<SingleReply> findById(String id);
    Mono<String> singleReply(IntentApiRequestDto dto);
}
