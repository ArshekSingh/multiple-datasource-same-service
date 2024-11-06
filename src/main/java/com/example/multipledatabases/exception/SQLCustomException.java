package com.example.multipledatabases.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SQLCustomException extends RuntimeException {

    private final HttpStatus status;
    private final int code;
    private Object data;

    public SQLCustomException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.code = status.value();
    }

    public SQLCustomException(HttpStatus status, String message, Object data) {
        super(message);
        this.status = status;
        this.code = status.value();
        this.data = data;
    }

}
