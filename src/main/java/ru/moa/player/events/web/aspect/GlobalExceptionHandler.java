package ru.moa.player.events.web.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.moa.player.events.exception.HasChildrenException;
import ru.moa.player.events.exception.NotFoundException;
import ru.moa.player.events.exception.OptimisticLockException;
import ru.moa.player.events.service.MessageService;
import ru.moa.player.events.web.transfer.ErrorResponseDto;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    private final MessageService messageService;

    private void logRequestException(HttpServletRequest request, Exception exception) {
        log.debug("Unexpected exception processing request: " + request.getRequestURI());
        log.error("Exception: ", exception);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseDto> resolveDefaultException(HttpServletRequest request, Exception exception) {
        logRequestException(request, exception);

        ErrorResponseDto response = ErrorResponseDto.build(String.format(messageService.getMessage("unexpected.exception"), exception));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(OptimisticLockException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseDto> resolveOptimisticLock(HttpServletRequest request, Exception exception) {
        logRequestException(request, exception);

        return new ResponseEntity<>(ErrorResponseDto.build(messageService.getMessage("stale.object.exception")), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseDto> resolveNotFoundException(HttpServletRequest request, Exception exception) {
        logRequestException(request, exception);

        return new ResponseEntity<>(ErrorResponseDto.build(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HasChildrenException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseDto> resolveHasChildrenException(HttpServletRequest request, Exception exception) {
        logRequestException(request, exception);

        return new ResponseEntity<>(ErrorResponseDto.build(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
