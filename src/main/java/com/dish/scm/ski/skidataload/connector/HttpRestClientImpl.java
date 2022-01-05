package com.dish.scm.ski.skidataload.connector;


import com.dish.scm.ski.skidataload.errorhandler.ApiResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class HttpRestClientImpl {
    private final RestTemplate restTemplate;


    @Autowired
    public HttpRestClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public <T> ResponseEntity<T> callApi(RequestEntity<?> request, Class<T> responseType)
            throws ApiResponseException {

        ResponseEntity<T> response;
        response = restTemplate.exchange(request,responseType);

        if (StringUtils.isEmpty(response)) {
            log.error("Response is null");
            throw new ApiResponseException("Response is null");
        }        

        return response;
    }


}
