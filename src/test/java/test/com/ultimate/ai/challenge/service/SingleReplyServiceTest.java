package test.com.ultimate.ai.challenge.service;

import com.ultimate.ai.challenge.dto.IntentApiRequestDto;
import com.ultimate.ai.challenge.dto.IntentApiResponseDto;
import com.ultimate.ai.challenge.model.SingleReply;
import com.ultimate.ai.challenge.repository.SingleReplyRepository;
import com.ultimate.ai.challenge.service.SingleReplyServiceImpl;
import com.ultimate.ai.challenge.service.UltimateAIRestServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
public class SingleReplyServiceTest {

    private static SingleReply singleReply;
    private static IntentApiRequestDto dto1;
    private static IntentApiRequestDto dto2;
    private static IntentApiResponseDto responseDto1;
    private static IntentApiResponseDto responseDto2;

    @Mock
    private UltimateAIRestServiceImpl ultimateAIRestService;
    @Mock
    private SingleReplyRepository singleReplyRepository;

    @InjectMocks
    private SingleReplyServiceImpl singleReplyService;


    @BeforeAll
    public static void init() {
        singleReply  = new SingleReply("Greeting", "I am fine");
        dto1         = new IntentApiRequestDto("5f74865056d7bb000fcd39ff","Hello");
        dto2         = new IntentApiRequestDto("50fcd39ff","Sup");
        responseDto1 = new IntentApiResponseDto(1.0,"Greeting");
        responseDto2 = new IntentApiResponseDto(0.1,"Ask");
    }

    @Test
    public void test_find_by_id(){
        Mockito.when(singleReplyRepository.findById("Greeting")).thenReturn(Mono.just(singleReply));
        assertThat(singleReplyService.findById("Greeting").block().getIntent(), is("Greeting"));
        Mockito.verify(singleReplyRepository, Mockito.times(1)).findById("Greeting");
    }

    @Test
    public void test_singleReply_no_intent_found_in_db(){
        List<IntentApiResponseDto> mockedResponse = new ArrayList<>();
        mockedResponse.add(responseDto2);

        Mockito.when(ultimateAIRestService.processHttpPost(dto2,"/intents")).thenReturn(mockedResponse);
        Mockito.when(singleReplyRepository.findById("Ask")).thenReturn(Mono.empty());

        assertThat(singleReplyService.singleReply(dto2).block(), is("Not_Available"));

        Mockito.verify(ultimateAIRestService, Mockito.times(1)).processHttpPost(dto2,"/intents");
        Mockito.verify(singleReplyRepository, Mockito.times(1)).findById("Ask");

    }

    @Test
    public void test_singleReply_intent_found_in_db(){
        List<IntentApiResponseDto> mockedResponse = new ArrayList<>();
        mockedResponse.add(responseDto1);

        Mockito.when(ultimateAIRestService.processHttpPost(dto1,"/intents")).thenReturn(mockedResponse);
        Mockito.when(singleReplyRepository.findById("Greeting")).thenReturn(Mono.just(singleReply));

        assertThat(singleReplyService.singleReply(dto1).block(), is("Greeting"));

        Mockito.verify(ultimateAIRestService, Mockito.times(1)).processHttpPost(dto1,"/intents");
        Mockito.verify(singleReplyRepository, Mockito.times(1)).findById("Greeting");

    }

    @Test
    public void test_singleReply_highest_precedence(){
        List<IntentApiResponseDto> mockedResponse = new ArrayList<>();
        mockedResponse.add(responseDto1);
        mockedResponse.add(responseDto2);

        Mockito.when(ultimateAIRestService.processHttpPost(dto1,"/intents")).thenReturn(mockedResponse);
        Mockito.when(singleReplyRepository.findById("Greeting")).thenReturn(Mono.just(singleReply));

        assertThat(singleReplyService.singleReply(dto1).block(), is(responseDto1.getIntent()));

        Mockito.verify(ultimateAIRestService, Mockito.times(1)).processHttpPost(dto1,"/intents");
        Mockito.verify(singleReplyRepository, Mockito.times(1)).findById("Greeting");

    }
}
