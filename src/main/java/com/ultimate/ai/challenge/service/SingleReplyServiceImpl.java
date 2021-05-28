package com.ultimate.ai.challenge.service;

import com.ultimate.ai.challenge.dto.IntentApiRequestDto;
import com.ultimate.ai.challenge.dto.IntentApiResponseDto;
import com.ultimate.ai.challenge.model.SingleReply;
import com.ultimate.ai.challenge.repository.SingleReplyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;

@Service
public class SingleReplyServiceImpl implements SingleReplyService{
    private static final Logger log = LoggerFactory.getLogger(SingleReplyServiceImpl.class);

    private SingleReplyRepository singleReplyRepository;
    private UltimateAIRestService ultimateAIRestService;

    public SingleReplyServiceImpl(SingleReplyRepository singleReplyRepository, UltimateAIRestService ultimateAIRestService) {
        this.singleReplyRepository = singleReplyRepository;
        this.ultimateAIRestService = ultimateAIRestService;
    }

    /**
     *
     * @param id which is the intent
     * @return
     */
    @Override
    public Mono<SingleReply> findById(String id) {
        log.info("find intent by ID");
        return singleReplyRepository.findById(id);
    }

    @Override
    public Mono<String> singleReply(final IntentApiRequestDto dto) {
        List<IntentApiResponseDto> intents = ultimateAIRestService.processHttpPost(dto,"/intents");
        intents.stream().sorted(Comparator.comparingDouble(IntentApiResponseDto::getConfidence).reversed());
        String intent = intents.get(0).getIntent();
        //Get the database Record
        SingleReply singleReply = findById(intent).block();
        //We reply Not_Available if no record found for the intent in the DB
        return Mono.just(singleReply==null?"Not_Available":singleReply.getIntent());
    }
}
