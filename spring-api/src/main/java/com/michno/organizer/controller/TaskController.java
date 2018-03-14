package com.michno.organizer.controller;

import com.michno.organizer.exception.IncorrectInputDataException;
import com.michno.organizer.exception.ResourceNotFoundException;
import com.michno.organizer.model.Task;
import com.michno.organizer.model.TodoList;
import com.michno.organizer.payload.ApiResponse;
import com.michno.organizer.payload.TaskResponse;
import com.michno.organizer.repository.TaskRepository;
import com.michno.organizer.repository.TodoListRepository;
import com.michno.organizer.repository.UserRepository;
import com.michno.organizer.security.CurrentUser;
import com.michno.organizer.security.UserPrincipal;
import com.michno.organizer.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class TaskController {

    @Autowired
    TodoListRepository todoListRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/task/{id}")
    @PreAuthorize("hasRole('USER')")
    public TaskResponse getTask(@PathVariable("id") Long id, @CurrentUser UserPrincipal currentUser) {
        Task task = taskRepository.getOne(id);
        if (task == null || task.getCreatedBy() != currentUser.getId())
            throw new ResourceNotFoundException("Task", "id", id);

        return ModelMapper.mapTaskToTaskResponse(task);
    }

    @PostMapping(value = "/list/{listId}/task/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addTask(@PathVariable("listId") Long id, @RequestBody @Valid Task task, Errors errors, UriComponentsBuilder ucb) {
        if (errors.hasErrors())
            throw new IncorrectInputDataException(errors);

        TodoList list = todoListRepository.findOne(id);
        list.addTask(task);
        Task newTask = taskRepository.save(task);

        HttpHeaders headers = new HttpHeaders();
        URI locationURI = ucb.path("/list/")
                .path(String.valueOf(list.getId()))
                .path("/task")
                .path(String.valueOf(newTask.getId()))
                .build()
                .toUri();
        headers.setLocation(locationURI);


        return ResponseEntity.created(locationURI).body(new ApiResponse(true, "Task created successfully"));
    }

    @PutMapping(value = "/task/{id}")
    @PreAuthorize("hasRole('USER')")
    public TaskResponse updateTask(@RequestBody @Valid Task task, Errors errors, @PathVariable Long id, @CurrentUser UserPrincipal currentUser) {
        if (errors.hasErrors()) throw new IncorrectInputDataException(errors);
        if (task.getCreatedBy() != currentUser.getId())
            throw new ResourceNotFoundException("Task", "id", id);

        taskRepository.save(task);
        return ModelMapper.mapTaskToTaskResponse(task);
    }

    @DeleteMapping(value = "/task/{id}")
    @PreAuthorize("hasRole('USER')")
    public void deleteTask(@PathVariable Long id, @CurrentUser UserPrincipal currentUser) {
        Task task = taskRepository.findOne(id);
        if (task.getCreatedBy() != currentUser.getId())
            throw new ResourceNotFoundException("Task", "id", id);
        taskRepository.delete(id);
    }
}
