package com.aifarms.java_common.exceptions;

public class ErrorResponse {
    private String message;
    private String code;
    private String status;

    public ErrorResponse(String message, String code, String status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }

    public ErrorResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public ErrorResponse() {
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }
}
