package com.daralamane.BackendV1.exception;

import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {
    public static ResponseEntity<Object> build(ApiError apiError){
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
