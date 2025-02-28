package com.example.airxelerateapi.exception;

import com.example.airxelerateapi.dto.response.MessageResult;
import com.example.airxelerateapi.dto.response.Result;
import com.example.airxelerateapi.enumeration.MessageStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Result<List<MessageResult>> handleBusinessException(BusinessException ex) {
        return  Result.createResultWithBody(HttpStatus.NOT_ACCEPTABLE,
                new MessageResult(ex.getMessage(), MessageStatus.ERROR), ex.getMessages());
    }

    @ExceptionHandler(TechnicalException.class)
    public Result<Void> handleTechnicalException(TechnicalException ex) {
        return  Result.createResultWithoutBody(HttpStatus.INTERNAL_SERVER_ERROR, new MessageResult(ex.getMessage(), MessageStatus.ERROR));
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception ex) {
        return  Result.createResultWithoutBody(HttpStatus.INTERNAL_SERVER_ERROR, new MessageResult(ex.getMessage(), MessageStatus.ERROR));
    }
}
