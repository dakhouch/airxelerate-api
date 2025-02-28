package com.example.airxelerateapi.dto.response;

import com.example.airxelerateapi.enumeration.ResultStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.LocalDateTime;

public record Result<T>(HttpStatus status,
                        Instant createdAt,
                        MessageResult message, T body) {


    public static <T> Result<T> createResultWithBody(HttpStatus status,MessageResult message, T body) {
        return new Result<>(status,Instant.now(), message, body);
    }

    public static <T> Result<T> createResultWithoutBody(HttpStatus status, MessageResult message) {
        return new Result<>(status, Instant.now(), message, null);
    }
}