package ru.moa.player.events.web.transfer;

import lombok.Data;
import ru.moa.player.events.exception.BusinessValidationException;

import java.util.Arrays;

@Data
public class ErrorResponseDto {
    private boolean informative = false;
    private String message;
    private String[] stacktrace;
    private String field;

    public static ErrorResponseDto build(boolean informative, String message, String field, StackTraceElement[] stacktrace) {
        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse.setInformative(informative);
        errorResponse.setMessage(message);
        errorResponse.setField(field);
        if (stacktrace != null) {
            errorResponse.setStacktrace(Arrays.stream(stacktrace).map(StackTraceElement::toString).toArray(String[]::new));
        }
        return errorResponse;
    }

    public static ErrorResponseDto build(String message, BusinessValidationException exception){
        return build(false, message, (exception != null
                && exception.getErrors() != null && !exception.getErrors().isEmpty()) ?
                exception.getErrors().get(0).getField() : null , null);
    }

    public static ErrorResponseDto build(String message, Exception exception) {
        return build(false, message, null, exception != null ? exception.getStackTrace() : null);
    }

    public static ErrorResponseDto build(String message) {
        return build(true, message, null, null);
    }
}

