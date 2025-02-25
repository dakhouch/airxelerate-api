package com.example.airxelerateapi.dto;

import com.example.airxelerateapi.enumeration.ResultStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;


public class Result<T> {
    private ResultStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdAt;
    private MessageResult message;
    private T body;

    private Result(ResultStatus status, LocalDateTime createdAt, MessageResult message, T body) {
        this.status = status;
        this.createdAt = createdAt;
        this.message = message;
        this.body = body;
    }
    
    public static <T> Result<T> createResultWithBody(ResultStatus status, MessageResult message, T body) {
        return new Result<>(status, LocalDateTime.now(), message, body);
    }

    public static <T> Result<T> createResultWithoutBody(ResultStatus status, MessageResult message) {
        return new Result<>(status, LocalDateTime.now(), message, null);
    }
}