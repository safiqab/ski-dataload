package com.dish.scm.ski.skidataload.errorhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Slf4j
public class CustomApiError implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError());
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        try {
            if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
                log.error("Response Server Error Code:{}", response.getStatusCode().value());
            } else if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
                log.error("Response Client Error Code:{}", response.getStatusCode().value());
                if (response.getStatusCode() == HttpStatus.NOT_FOUND)
                    log.error("Response exception Error Code:{}", response.getStatusCode().value());
            }
        }
        catch (Exception e) {
            log.error("Exception while printing response error.");
        }
    }
}
