package com.michno.organizer.exception;


import org.springframework.validation.Errors;

public class IncorrectInputDataException extends RuntimeException {
    Errors errors;

    public IncorrectInputDataException(Errors errors) {
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
