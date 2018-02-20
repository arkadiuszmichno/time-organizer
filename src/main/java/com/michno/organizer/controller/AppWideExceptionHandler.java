package com.michno.organizer.controller;

import com.michno.organizer.errors.DuplicateToDoListException;
import com.michno.organizer.errors.IncorrectInputDataException;
import com.michno.organizer.errors.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
class AppWideExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleEntityNotFound(EntityNotFoundException e) {
        int entityId = e.getEntityId();
        String entityName = e.getName();
        return new Error(entityName + " [" + entityId + "] not found");
    }

    @ExceptionHandler(DuplicateToDoListException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Error duplicateFound(DuplicateToDoListException e) {
        String name = e.getName();
        return new Error("List " + name + " already exists");
    }

    @ExceptionHandler(IncorrectInputDataException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Error handleIncorrectInputData(IncorrectInputDataException e) {
        Errors errors = e.getErrors();
        return new Error("Bad input data: " + errors.getFieldErrors());
    }
}
