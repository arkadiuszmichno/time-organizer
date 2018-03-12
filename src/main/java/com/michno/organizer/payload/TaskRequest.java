package com.michno.organizer.payload;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.time.LocalDate;

public class TaskRequest {

    @NotBlank
    @Size(min = 3)
    private String name;

    private String priority;

    private String description;

    private LocalDate endDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
