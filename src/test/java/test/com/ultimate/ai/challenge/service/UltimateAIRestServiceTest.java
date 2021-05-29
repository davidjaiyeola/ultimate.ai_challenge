package test.com.ultimate.ai.challenge.service;

import com.ultimate.ai.challenge.dto.IntentApiDto;
import com.ultimate.ai.challenge.dto.IntentApiRequestDto;
import com.ultimate.ai.challenge.dto.IntentApiResponseDto;
import com.ultimate.ai.challenge.service.UltimateAIRestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
public class UltimateAIRestServiceTest {

    @Mock
    private UltimateAIRestService ultimateAIRestService;

    @Test
    void getIntents(){
        IntentApiRequestDto requestDto = new IntentApiRequestDto("5f74865056d7bb000fcd39ff","Hello");
        IntentApiResponseDto mockedResponse = new IntentApiResponseDto();
        mockedResponse.getIntents().add(new IntentApiDto(1.0,"Greeting"));
        mockedResponse.getIntents().add(new IntentApiDto(0.1,"I dont know"));

        Mockito.when(ultimateAIRestService.processHttpPost(requestDto,"/intents")).thenReturn(mockedResponse);

        List<IntentApiDto> responseList = ultimateAIRestService.processHttpPost(requestDto,"/intents").getIntents();
        assertThat(responseList.size(), is(2));
        Mockito.verify(ultimateAIRestService, Mockito.times(1)).processHttpPost(requestDto,"/intents");

    }
}

