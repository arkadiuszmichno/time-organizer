package com.michno.organizer.dao;


import com.michno.organizer.model.Task;

import java.util.List;

public interface TaskDAO {
    List<Task> findAllTasks();

    Task findTaskById(int id);

    Task createTask(Task task);

    void updateTask(int id, Task task);

    void deleteTask(int id);
}
