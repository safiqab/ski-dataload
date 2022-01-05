package com.dish.scm.ski.skidataload.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ApiUtil {
    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static String convertObj2json(Object object) {
        try {
            if (null!=object) {
                return objectMapper.writeValueAsString(object);
            }
            return null;
        }
        catch (Exception e) {
            return object.toString();
        }
    }



    public static HttpHeaders getJsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
