package com.michno.organizer.service;

import com.michno.organizer.exception.ResourceNotFoundException;
import com.michno.organizer.model.Task;
import com.michno.organizer.model.TodoList;
import com.michno.organizer.payload.TaskRequest;
import com.michno.organizer.repository.TaskRepository;
import com.michno.organizer.security.CurrentUser;
import com.michno.organizer.security.UserPrincipal;
import com.michno.organizer.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TodoListService todoListService;


    public Task findById(Long id, UserPrincipal currentUser) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));

        if (task.getCreatedBy() != currentUser.getId()) {
            throw new ResourceNotFoundException("Task", "id", id);
        }
        return task;
    }

    public Task create(TaskRequest taskRequest, Long listId, @CurrentUser UserPrincipal currentUser) {
        TodoList list = todoListService.findByIdAndUser(listId, currentUser);

        Task task = ModelMapper.mapTaskRequestToTask(taskRequest);
        list.addTask(task);

        return taskRepository.save(task);
    }

    public Task updateTask(Long taskId, TaskRequest taskRequest, UserPrincipal currentUser) {

        Task task = findById(taskId, currentUser);

        task.setName(taskRequest.getName());
        task.setDescription(taskRequest.getDescription());
        task.setPriority(taskRequest.getPriority());
        task.setEndDate(taskRequest.getEndDate());

        return taskRepository.save(task);

    }

    public void deleteTask(Long taskId, UserPrincipal currentUser) {
        Task task = findById(taskId, currentUser);

        taskRepository.delete(task);
    }
}
