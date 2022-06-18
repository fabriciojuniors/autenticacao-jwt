package com.jwt.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private List<ApiErrorField> errors;

    public ApiError(LocalDateTime timestamp, String message, List<ApiErrorField> errors) {
        this.timestamp = timestamp;
        this.message = message;
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ApiErrorField> getErrors() {
        return errors;
    }

    public void setErrors(List<ApiErrorField> errors) {
        this.errors = errors;
    }
}
