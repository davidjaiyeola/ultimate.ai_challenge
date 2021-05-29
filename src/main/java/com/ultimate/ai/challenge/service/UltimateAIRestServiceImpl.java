package com.ultimate.ai.challenge.service;

import com.google.gson.Gson;
import com.ultimate.ai.challenge.dto.IntentApiRequestDto;
import com.ultimate.ai.challenge.dto.IntentApiResponseDto;
import com.ultimate.ai.challenge.util.HttpTimeout;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UltimateAIRestServiceImpl implements UltimateAIRestService {

    private static Logger logger = LoggerFactory.getLogger(UltimateAIRestServiceImpl.class);

    private Gson gson;
    @Value("${ultimate-ai.baseURL}")
    public String baseURL;
    @Value("${ultimate-ai.apiKey}")
    private String apiKey;

    public UltimateAIRestServiceImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public IntentApiResponseDto processHttpPost(IntentApiRequestDto request, String path) {
        CloseableHttpResponse closeableHttpResponse = null;
        String responseObject = null;
        Long startTime = System.nanoTime();

        try {
            CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(HttpTimeout.getTimeoutConfig(30000,30000,60000)).build();
            HttpPost httpPost = new HttpPost(baseURL+path);
            StringEntity entity = new StringEntity(gson.toJson(request));
            httpPost.setEntity(entity);

            buildHeaders(httpPost);

            logger.info("Making request to backend..... ");
            closeableHttpResponse = client.execute(httpPost);

            responseObject = EntityUtils.toString(closeableHttpResponse.getEntity());

            logger.info("Response -> " + responseObject);
            int responseCode = closeableHttpResponse.getStatusLine().getStatusCode();

            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Long endTime5 = System.nanoTime() - startTime;
        Double timeMills5 = (double) (Double.valueOf(endTime5) / Double.valueOf(1000000));
        logger.info("Completed request in" + " ->" + endTime5 + "ns" + " >>> " + timeMills5 + "ms");

        IntentApiResponseDto intentApiResponseDto = gson.fromJson(responseObject, IntentApiResponseDto.class);
        return intentApiResponseDto;
    }



    public void buildHeaders(HttpRequestBase request){
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Authorization", apiKey);
    }
}
