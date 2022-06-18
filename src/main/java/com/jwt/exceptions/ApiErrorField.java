package com.jwt.exceptions;

public class ApiErrorField {

    public String field;
    public String message;

    public ApiErrorField(String field, String message){
        this.field = field;
        this.message = message;
    }

}
