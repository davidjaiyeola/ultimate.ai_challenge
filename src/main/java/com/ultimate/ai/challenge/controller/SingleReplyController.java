package com.ultimate.ai.challenge.controller;

import com.ultimate.ai.challenge.dto.IntentApiRequestDto;
import com.ultimate.ai.challenge.dto.SingleReplyResponseDto;
import com.ultimate.ai.challenge.service.SingleReplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController("/api/v1")
public class SingleReplyController {
    private SingleReplyService singleReplyService;

    public SingleReplyController(SingleReplyService singleReplyService) {
        this.singleReplyService = singleReplyService;
    }

    @PostMapping( value="/reply", produces ="application/json")
    public Mono<ResponseEntity<SingleReplyResponseDto>> reply(@RequestBody IntentApiRequestDto dto) {
        return singleReplyService.singleReply(dto).map(intent -> ResponseEntity.ok().body(new SingleReplyResponseDto(intent)));
    }
}
