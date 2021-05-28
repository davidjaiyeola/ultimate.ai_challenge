package com.ultimate.ai.challenge.repository;

import com.ultimate.ai.challenge.model.SingleReply;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SingleReplyRepository extends ReactiveMongoRepository<SingleReply, String> {
}
