package com.springsecurity.stay_ease_jwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<String> alreadyExistExceptionHandler(AlreadyExistException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LimitExceedException.class)
    public ResponseEntity<String> handleLimitExceed(LimitExceedException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotAvailableException.class)
    public ResponseEntity<String> notAvailableExceptionHandler(NotAvailableException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}

