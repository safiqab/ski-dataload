package com.dish.scm.ski.skidataload.connector;


import com.dish.scm.ski.skidataload.config.ApiEndPointConfig;
import com.dish.scm.ski.skidataload.model.response.SkiReference;
import com.dish.scm.ski.skidataload.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

//import static com.dish.scm.ski.skidataload.util.ApiUtil.gethttpHeaders;

@Component
@Slf4j
public class ApiConnector {

    private final HttpRestClientImpl httpRestClientImpl;
    private final ApiEndPointConfig apiEndPointConfig;


    @Autowired
    public ApiConnector(HttpRestClientImpl httpRestClientImpl, ApiEndPointConfig apiEndPointConfig) {
        this.httpRestClientImpl = httpRestClientImpl;
        this.apiEndPointConfig = apiEndPointConfig;
    }

    public ResponseEntity<SkiReference> getReferenceData(String url, HttpHeaders headers) {
        return httpRestClientImpl.callApi(RequestUtil.get(url, headers), SkiReference.class);
    }

    public ResponseEntity<SkiReference> addReferenceData(RequestEntity<?> request) {
        return httpRestClientImpl.callApi(request, SkiReference.class);
    }


}

