package com.dish.scm.ski.skidataload.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.util.MultiValueMap;

import java.net.URI;

public class RequestUtil {
    public static RequestEntity<?> get(String uri) {
        return RequestEntity.get(URI.create(uri)).build();
    }

    public static RequestEntity<?> get(String uri, HttpHeaders headers) {
        return RequestEntity.get(URI.create(uri)).headers(headers).build();
    }

    public static<T> RequestEntity<T> post(String uri,T body) {
        return RequestEntity.post(URI.create(uri)).body(body);
    }

    public static<T> RequestEntity<T> post(String uri,HttpHeaders headers) {
        return RequestEntity.post(URI.create(uri)).headers(headers).body(null);
    }

    public static<T> RequestEntity<T> post(String uri,T body,HttpHeaders headers) {
        return RequestEntity.post(URI.create(uri)).headers(headers).body(body);
    }

    public static<T> RequestEntity<T> patch(String uri,T body,HttpHeaders headers) {
        return RequestEntity.patch(URI.create(uri)).headers(headers).body(body);
    }

    public static<T> RequestEntity<T> post(String uri, MultiValueMap<String,String> body, MediaType mtype, HttpHeaders headers) {
        return (RequestEntity<T>) RequestEntity.post(URI.create(uri)).contentType(mtype).headers(headers).body(body);
    }

    public static <T> RequestEntity<?> delete(String uri,HttpHeaders headers ) {
        return RequestEntity.delete(URI.create(uri)).headers(headers).build();
    }


    public static<T> RequestEntity<T> put(String uri,T body) {
        return RequestEntity.post(URI.create(uri)).body(body);
    }

    public static<T> RequestEntity<T> put(String uri,T body,HttpHeaders headers) {
        return RequestEntity.post(URI.create(uri)).headers(headers).body(body);
    }
}
