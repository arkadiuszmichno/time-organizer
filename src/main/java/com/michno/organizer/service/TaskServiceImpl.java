package com.michno.organizer.service;

import com.michno.organizer.dao.TaskDAO;
import com.michno.organizer.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDAO taskDAO;

    public List<Task> getAllTasks() {
        return taskDAO.findAllTasks();
    }

    @Override
    public Task getTask(int id) {
        return taskDAO.findTaskById(id);
    }

    @Override
    public void createTask(Task task) {
        taskDAO.createTask(task);
    }

    @Override
    public void updateTask(int id, Task task) {
        taskDAO.updateTask(id, task);
    }

    @Override
    public void deleteTask(int id) {
        taskDAO.deleteTask(id);
    }
}
