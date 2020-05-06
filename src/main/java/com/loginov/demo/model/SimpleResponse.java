package com.loginov.demo.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Data
public class SimpleResponse {
    @NonNull
    private final Integer status;
    @NonNull
    private final String message;

    public static SimpleResponse of(final HttpStatus httpStatus) {
        return new SimpleResponse(httpStatus.value(), httpStatus.name());
    }
}
