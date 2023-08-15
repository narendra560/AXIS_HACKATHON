package com.aifarms.java_common.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
public class SystemException extends Exception{
    public SystemException(String message){
        super(message);
    }

    public SystemException(String message, Throwable cause){
        super(message, cause);
    }
}
