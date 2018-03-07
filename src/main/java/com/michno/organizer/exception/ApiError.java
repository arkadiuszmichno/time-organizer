package com.michno.organizer.exception;


import org.springframework.http.HttpStatus;

public class ApiError {
    private HttpStatus httpStatus;
    private String message;

    public ApiError(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
