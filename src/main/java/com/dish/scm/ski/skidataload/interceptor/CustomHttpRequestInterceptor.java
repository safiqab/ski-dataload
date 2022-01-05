package com.dish.scm.ski.skidataload.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class CustomHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        //  logRequest(request,body);
        return execution.execute(request, body);
    }

    private void logRequest(HttpRequest req,byte[] body) {
        log.debug("Request:{} for URL {} ", new String(body, StandardCharsets.UTF_8), req.getURI());
    }
}

