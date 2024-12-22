package com.springsecurity.stay_ease_jwt.exception;

public class LimitExceedException extends RuntimeException{

    public LimitExceedException(){}

    public LimitExceedException(String message) {
        super(message);
    }

}
