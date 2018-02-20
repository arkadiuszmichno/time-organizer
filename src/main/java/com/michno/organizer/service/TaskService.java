package com.michno.organizer.service;


import com.michno.organizer.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();

    Task getTask(int id);

    Task createTask(Task task);

    void updateTask(int id,Task task);

    void deleteTask(int id);
}
