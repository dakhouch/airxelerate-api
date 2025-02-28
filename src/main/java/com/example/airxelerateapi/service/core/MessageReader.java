package com.example.airxelerateapi.service.core;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MessageReader {
    MessageSource messageSource;

    public String getMessage(String code) {
        return messageSource.getMessage(code, null,
                LocaleContextHolder.getLocale());
    }

    public String getMessage(String code, Object[] args, String defaultMessage) {
        return messageSource.getMessage(code, args, defaultMessage, LocaleContextHolder.getLocale());

    }

    public String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, "!" + code + "!", LocaleContextHolder.getLocale());

    }

}
