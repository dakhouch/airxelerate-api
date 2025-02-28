package com.example.airxelerateapi.exception;

import com.example.airxelerateapi.dto.response.MessageResult;
import com.example.airxelerateapi.dto.response.Result;
import com.example.airxelerateapi.enumeration.MessageStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException ex) {
        return  Result.createResultWithoutBody(ex.getStatus(), MessageResult.getMessageResultWithoutField(ex.getMessage(), MessageStatus.ERROR));
    }

    @ExceptionHandler(TechnicalException.class)
    public Result<Void> handleTechnicalException(TechnicalException ex) {
        return  Result.createResultWithoutBody(HttpStatus.INTERNAL_SERVER_ERROR, MessageResult.getMessageResultWithoutField(ex.getMessage(), MessageStatus.ERROR));
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception ex) {
        return  Result.createResultWithoutBody(HttpStatus.INTERNAL_SERVER_ERROR, MessageResult.getMessageResultWithoutField(ex.getMessage(), MessageStatus.ERROR));
    }
}
