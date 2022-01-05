package com.dish.scm.ski.skidataload.errorhandler;

public class ApiResponseException extends RuntimeException {
    public ApiResponseException(String message) {
        super(message);
    }
}
