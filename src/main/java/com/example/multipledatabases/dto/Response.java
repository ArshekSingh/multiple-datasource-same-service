package com.example.multipledatabases.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Response<T extends Serializable> implements Serializable {

    @Serial
    private static final long serialVersionUID = 3175050647367470786L;

    private HttpStatus status;
    private String message;
    private int code;
    private T data;


    public Response(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.code = status.value();
        this.data = data;
    }

    public Response(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.code = status.value();
    }

}
