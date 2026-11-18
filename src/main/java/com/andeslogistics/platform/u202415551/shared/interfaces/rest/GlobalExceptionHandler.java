package com.andeslogistics.platform.u202415551.shared.interfaces.rest;

import com.andeslogistics.platform.u202415551.shared.interfaces.resources.MessageResource;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResource> handleValidation(MethodArgumentNotValidException exception) {
        var message = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResource(message));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<MessageResource> handleNotReadable(HttpMessageNotReadableException exception) {
        return build(HttpStatus.BAD_REQUEST, "error.invalid.request");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MessageResource> handleDataIntegrity(DataIntegrityViolationException exception) {
        return build(HttpStatus.CONFLICT, "delivery.order.duplicate");
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<MessageResource> handleNotFound(NoResourceFoundException exception) {
        return build(HttpStatus.NOT_FOUND, "error.not.found");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResource> handleUnexpected(Exception exception) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "error.unexpected");
    }

    private ResponseEntity<MessageResource> build(HttpStatus status, String key) {
        var message = messageSource.getMessage(key, null, key, LocaleContextHolder.getLocale());
        return ResponseEntity.status(status).body(new MessageResource(message));
    }
}
