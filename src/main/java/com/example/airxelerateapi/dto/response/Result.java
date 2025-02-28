package com.example.airxelerateapi.dto.response;

import org.springframework.http.HttpStatus;
import java.time.Instant;


public record Result<T>(Integer status,
                        Instant createdAt,
                        MessageResult message, T body) {


    public static <T> Result<T> createResultWithBody(Integer status,MessageResult message, T body) {
        return new Result<>(status,Instant.now(), message, body);
    }

    public static <T> Result<T> createResultWithoutBody(Integer status, MessageResult message) {
        return new Result<>(status, Instant.now(), message, null);
    }
}