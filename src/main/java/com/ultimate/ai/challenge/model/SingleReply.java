package com.ultimate.ai.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "SingleReplies")
public class SingleReply {
    @Id
    protected String intent;
    protected String response;
}
