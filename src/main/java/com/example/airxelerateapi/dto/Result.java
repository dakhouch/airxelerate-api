package com.example.airxelerateapi.dto;

import com.example.airxelerateapi.enumeration.ResultStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record Result<T>( ResultStatus status,
                        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
                        LocalDateTime createdAt,
                        MessageResult message, T body) {


    public static <T> Result<T> createResultWithBody(ResultStatus status, MessageResult message, T body) {
        return new Result<>(status, LocalDateTime.now(), message, body);
    }

    public static <T> Result<T> createResultWithoutBody(ResultStatus status, MessageResult message) {
        return new Result<>(status, LocalDateTime.now(), message, null);
    }
}