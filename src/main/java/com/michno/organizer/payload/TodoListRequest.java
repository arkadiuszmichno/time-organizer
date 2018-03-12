package com.michno.organizer.payload;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class TodoListRequest {

    @NotBlank
    @Size(min = 3)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
