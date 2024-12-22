package com.springsecurity.stay_ease_jwt.exception;

public class AlreadyExistException extends RuntimeException {

    public AlreadyExistException() {}
    
    public AlreadyExistException(String message) {
        super(message);
    }

}
