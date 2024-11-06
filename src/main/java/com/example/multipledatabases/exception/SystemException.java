package com.example.multipledatabases.exception;

import org.springframework.http.HttpStatus;

public class SystemException extends Exception{

    private final HttpStatus status;
    public SystemException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
