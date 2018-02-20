package com.michno.organizer.errors;


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
