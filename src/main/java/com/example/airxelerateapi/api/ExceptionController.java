package com.example.airxelerateapi.api;

import com.example.airxelerateapi.dto.MessageResult;
import com.example.airxelerateapi.dto.Result;
import com.example.airxelerateapi.enumeration.MessageStatus;
import com.example.airxelerateapi.enumeration.ResultStatus;
import com.example.airxelerateapi.exceptions.BusinessException;
import com.example.airxelerateapi.exceptions.TechnicalException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException ex) {
        return  Result.createResultWithoutBody(ResultStatus.ERROR, MessageResult.getMessageResultWithoutField(ex.getMessage(), MessageStatus.ERROR));
    }

    @ExceptionHandler(TechnicalException.class)
    public Result<Void> handleTechnicalException(TechnicalException ex) {
        return  Result.createResultWithoutBody(ResultStatus.ERROR, MessageResult.getMessageResultWithoutField(ex.getMessage(), MessageStatus.ERROR));
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(TechnicalException ex) {
        return  Result.createResultWithoutBody(ResultStatus.ERROR, MessageResult.getMessageResultWithoutField(ex.getMessage(), MessageStatus.ERROR));
    }
}
