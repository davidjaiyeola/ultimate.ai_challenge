package com.ultimate.ai.challenge.util;

import org.apache.http.client.config.RequestConfig;

public class HttpTimeout {
    public static RequestConfig getTimeoutConfig(int connectTimeout , int ConnectionRequestTimeout, int socketTimeout){
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(ConnectionRequestTimeout)
                .setSocketTimeout(socketTimeout).build();

        return requestConfig;
    }
}
