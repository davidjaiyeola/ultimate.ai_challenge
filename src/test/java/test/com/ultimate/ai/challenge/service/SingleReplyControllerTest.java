package test.com.ultimate.ai.challenge.service;

import com.ultimate.ai.challenge.controller.SingleReplyController;
import com.ultimate.ai.challenge.dto.IntentApiRequestDto;
import com.ultimate.ai.challenge.dto.SingleReplyResponseDto;
import com.ultimate.ai.challenge.service.SingleReplyServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(MockitoExtension.class)
public class SingleReplyControllerTest {
    @InjectMocks
    private SingleReplyController singleReplyController;
    @Mock
    private SingleReplyServiceImpl singleReplyService;

    private static IntentApiRequestDto dto1;
    private static SingleReplyResponseDto responseDto1;
    private static SingleReplyResponseDto responseDto2;


    @BeforeAll
    public static void init() {
        dto1                  = new IntentApiRequestDto("5f74865056d7bb000fcd39ff","Hello");
        responseDto1         = new SingleReplyResponseDto("Greeting");
        responseDto2         = new SingleReplyResponseDto("Not_Available");
    }

    @Test
    public void test_singleReply_intent_found_in_db(){
        Mockito.when(singleReplyService.singleReply(dto1)).thenReturn(Mono.just("Greeting"));
        ResponseEntity<SingleReplyResponseDto> reply = singleReplyController.reply(dto1).block();

        assertThat(reply.getStatusCode(), is(HttpStatus.OK));
        assertThat(reply.getBody(), notNullValue());
        assertThat(reply.getBody().getHighestIntent(), is(responseDto1.getHighestIntent()));

        Mockito.verify(singleReplyService, Mockito.times(1)).singleReply(dto1);
    }

    @Test
    public void test_singleReply_no_intent_found_in_db(){
        Mockito.when(singleReplyService.singleReply(dto1)).thenReturn(Mono.just("Not_Available"));
        ResponseEntity<SingleReplyResponseDto> reply = singleReplyController.reply(dto1).block();

        assertThat(reply.getStatusCode(), is(HttpStatus.OK));
        assertThat(reply.getBody(), notNullValue());
        assertThat(reply.getBody().getHighestIntent(), is(responseDto2.getHighestIntent()));

        Mockito.verify(singleReplyService, Mockito.times(1)).singleReply(dto1);
    }
}
