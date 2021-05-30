package com.ultimate.ai.challenge.controller;

import com.ultimate.ai.challenge.dto.IntentApiRequestDto;
import com.ultimate.ai.challenge.dto.SingleReplyResponseDto;
import com.ultimate.ai.challenge.service.SingleReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping({"/api/v1"})
@Api(value = "ChatBot", description = "Rest API for ChatBot operations", tags = "ChatBot API")
public class SingleReplyController {
    private SingleReplyService singleReplyService;

    public SingleReplyController(SingleReplyService singleReplyService) {
        this.singleReplyService = singleReplyService;
    }

    @ApiOperation(value = "Reply", response = SingleReplyResponseDto.class, produces = "application/json")
    @PostMapping( value="/reply", produces ="application/json")
    public Mono<ResponseEntity<SingleReplyResponseDto>> reply(@RequestBody IntentApiRequestDto dto) {
        return singleReplyService.singleReply(dto).map(intent -> ResponseEntity.ok().body(new SingleReplyResponseDto(intent)));
    }
}
