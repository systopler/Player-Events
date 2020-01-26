package ru.moa.player.events.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class MessageService {
    private static Locale russian = new Locale("ru", "RU");

    @Autowired
    private ResourceBundleMessageSource messageSource;

    public String getMessage(String code) {
        try {
            return messageSource.getMessage(code, null, russian);
        } catch (Exception e) {
            log.warn("Get message key='" + code + "' error:" + e.getMessage());
        }
        return code;
    }

    public String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, russian);
    }

    public String getMessage(String code, List<FieldError> errors){
        return errors != null && !errors.isEmpty() ? messageSource.getMessage(code, errors.get(0).getCodes(), russian) : "";
    }
}
