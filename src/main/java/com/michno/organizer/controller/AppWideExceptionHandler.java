package com.michno.organizer.controller;

import com.michno.organizer.exception.ApiError;
import com.michno.organizer.exception.DuplicateToDoListException;
import com.michno.organizer.exception.IncorrectInputDataException;
import com.michno.organizer.exception.EntityNotFoundException;
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
    public ApiError handleEntityNotFound(EntityNotFoundException e) {
        int entityId = e.getEntityId();
        String entityName = e.getName();
        return new ApiError(HttpStatus.NOT_FOUND, entityName + " [" + entityId + "] not found");
    }

    @ExceptionHandler(DuplicateToDoListException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError duplicateFound(DuplicateToDoListException e) {
        String name = e.getName();
        return new ApiError(HttpStatus.CONFLICT, "List " + name + " already exists");
    }

    @ExceptionHandler(IncorrectInputDataException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ApiError handleIncorrectInputData(IncorrectInputDataException e) {
        Errors errors = e.getErrors();
        return new ApiError(HttpStatus.NOT_ACCEPTABLE, "Bad input data: " + errors.getFieldErrors());
    }
}
