package com.example.airxelerateapi.exception;

import com.example.airxelerateapi.dto.response.MessageResult;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BusinessException extends Exception{
    private final HttpStatus status;
    private final List<MessageResult> messages;

    public BusinessException(String message, List<MessageResult> messages, HttpStatus status){
        super(message);
        this.status = status;
        this.messages = messages;
   }
    public BusinessException(String message, HttpStatus status){
        super(message);
        this.status = status;
        this.messages = new ArrayList<>();
    }


}
