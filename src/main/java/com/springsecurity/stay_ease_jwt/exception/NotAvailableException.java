package com.springsecurity.stay_ease_jwt.exception;

public class NotAvailableException extends RuntimeException{

    public NotAvailableException(){}

    public NotAvailableException(String message) {
        super(message);
    }

}
