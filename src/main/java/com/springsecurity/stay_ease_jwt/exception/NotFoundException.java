package com.springsecurity.stay_ease_jwt.exception;

public class NotFoundException  extends RuntimeException {

    public NotFoundException(){}

    public NotFoundException(String message) {
        super(message);
    }
}