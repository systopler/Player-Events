package ru.moa.player.events.exception;

import org.springframework.validation.FieldError;

import java.util.Collections;
import java.util.List;

public class BusinessValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private List<FieldError> errors;

    public BusinessValidationException(String field, String code, Object... args) {
        FieldError error = new FieldError("", field, null, false, new String[]{code}, args, null);
        errors = Collections.singletonList(error);
    }

    public BusinessValidationException(List<FieldError> errors) {
        this.errors = errors;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }
}