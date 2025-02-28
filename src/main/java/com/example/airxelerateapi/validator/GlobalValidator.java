package com.example.airxelerateapi.validator;

import com.example.airxelerateapi.dto.response.MessageResult;
import com.example.airxelerateapi.enumeration.MessageStatus;
import com.example.airxelerateapi.exception.BusinessException;
import com.example.airxelerateapi.service.core.MessageReader;
import com.example.airxelerateapi.util.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class GlobalValidator {
    private static final Integer MAX_PAGE_SIZE=60;
    private static final Integer MAX_PAGE_NUMBER=100;

    private final MessageReader messageReader;

    public void validatePageRequest(int pageNO,int pageSize) throws BusinessException {
        List<MessageResult> messages=new ArrayList<>();
        if(pageNO >MAX_PAGE_NUMBER) messages.add(new MessageResult(messageReader.getMessage(ExceptionMessage.INVALID_FIELD, new String[]{"page NO"}
        ), MessageStatus.WARNING));
        if(pageSize > MAX_PAGE_SIZE) messages.add(new MessageResult(messageReader.getMessage(ExceptionMessage.INVALID_FIELD, new String[]{"page Size"}
        ), MessageStatus.WARNING));
        if (!messages.isEmpty()) throw new BusinessException(messageReader.getMessage(ExceptionMessage.INVALID_FIELDS), messages, HttpStatus.BAD_REQUEST);
    }
}


