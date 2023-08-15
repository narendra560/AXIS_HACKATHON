package com.aifarms.java_common.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerAdvicer {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), "400");
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(SystemException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleSystemException(SystemException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), "500");
        return ResponseEntity.status(500).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleException(Exception e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), "500");
        return ResponseEntity.status(500).body(errorResponse);
    }
}
