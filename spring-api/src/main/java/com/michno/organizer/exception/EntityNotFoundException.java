package com.michno.organizer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
    private int entityId;
    private String name;

    public EntityNotFoundException(String name, int toDoListId) {
        this.name = name;
        this.entityId = toDoListId;
    }

    public int getEntityId() {
        return entityId;
    }

    public String getName() {
        return name;
    }
}
