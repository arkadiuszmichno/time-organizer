package com.michno.organizer.exception;


public class DuplicateToDoListException extends RuntimeException{
    private String name;

    public DuplicateToDoListException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
