package com.michno.organizer.service;

import com.michno.organizer.model.Task;
import com.michno.organizer.payload.TaskRequest;
import com.michno.organizer.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;


    public Task save(Task task, TaskRequest taskRequest) {
        task.setName(taskRequest.getName());
        task.setDescription(taskRequest.getDescription());
        task.setPriority(taskRequest.getPriority());
        task.setEndDate(taskRequest.getEndDate());

        return taskRepository.save(task);
    }
}
