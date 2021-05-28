package test.com.ultimate.ai.challenge.service;

import com.ultimate.ai.challenge.dto.IntentApiRequestDto;
import com.ultimate.ai.challenge.dto.IntentApiResponseDto;
import com.ultimate.ai.challenge.service.UltimateAIRestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
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
        List<IntentApiResponseDto> mockedResponse = new ArrayList<>();
        mockedResponse.add(new IntentApiResponseDto(1.0,"Greeting"));
        mockedResponse.add(new IntentApiResponseDto(0.1,"I dont know"));

        Mockito.when(ultimateAIRestService.processHttpPost(requestDto,"/intents")).thenReturn(mockedResponse);

        List<IntentApiResponseDto> responseList = ultimateAIRestService.processHttpPost(requestDto,"/intents");
        assertThat(responseList.size(), is(2));

    }
}

