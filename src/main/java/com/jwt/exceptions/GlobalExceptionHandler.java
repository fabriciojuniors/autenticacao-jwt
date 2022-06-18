package com.jwt.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<ApiErrorField> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(field -> new ApiErrorField(field.getField(), field.getDefaultMessage()))
                .collect(Collectors.toList());

        List<ApiErrorField> globalErrors = ex.getBindingResult()
                .getGlobalErrors()
                .stream()
                .map(field -> new ApiErrorField(field.getObjectName(), field.getDefaultMessage()))
                .collect(Collectors.toList());
        List<ApiErrorField> errors = new ArrayList<ApiErrorField>();
        errors.addAll(globalErrors);
        errors.addAll(fieldErrors);

        ApiError err = new ApiError(LocalDateTime.now(),
                "Erro de validação",
                errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(
            Exception ex,
            WebRequest request) {

        List<ApiErrorField> details = new ArrayList<ApiErrorField>();
        details.add(new ApiErrorField("Relacionamento",ex.getMessage()));
        ApiError err = new ApiError(
                LocalDateTime.now(),
                "Violação de relacionamento",
                details);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(
            Exception ex,
            WebRequest request) {

        List<ApiErrorField> details = new ArrayList<ApiErrorField>();
        details.add(new ApiErrorField("Aceso",ex.getMessage()));
        ApiError err = new ApiError(
                LocalDateTime.now(),
                "Acesso negado.",
                details);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }
}
