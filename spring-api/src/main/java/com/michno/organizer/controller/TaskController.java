package com.michno.organizer.controller;

import com.michno.organizer.exception.IncorrectInputDataException;
import com.michno.organizer.model.Task;
import com.michno.organizer.payload.TaskRequest;
import com.michno.organizer.payload.TaskResponse;
import com.michno.organizer.security.CurrentUser;
import com.michno.organizer.security.UserPrincipal;
import com.michno.organizer.service.TaskService;
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
@CrossOrigin("*")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping(value = "/tasks/{id}")
    @PreAuthorize("hasRole('USER')")
    public TaskResponse getTask(@PathVariable("id") Long id, @CurrentUser UserPrincipal currentUser) {
        Task task = taskService.findById(id, currentUser);

        return ModelMapper.mapTaskToTaskResponse(task);
    }

    @PostMapping(value = "/lists/{listId}/tasks")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addTask(@PathVariable("listId") Long listId, @RequestBody @Valid TaskRequest taskRequest, Errors errors,
                                     UriComponentsBuilder ucb, @CurrentUser UserPrincipal currentUser) {
        if (errors.hasErrors())
            throw new IncorrectInputDataException(errors);


        Task newTask = taskService.create(taskRequest, listId, currentUser);

        HttpHeaders headers = new HttpHeaders();
        URI locationURI = ucb.path("/list/")
                .path(String.valueOf(listId))
                .path("/task")
                .path(String.valueOf(newTask.getId()))
                .build()
                .toUri();
        headers.setLocation(locationURI);


        return ResponseEntity.created(locationURI).body(ModelMapper.mapTaskToTaskResponse(newTask));
    }

    @PutMapping(value = "/tasks/{id}")
    @PreAuthorize("hasRole('USER')")
    public TaskResponse updateTask(@PathVariable String id, @RequestBody @Valid TaskRequest taskRequest, Errors errors, @CurrentUser UserPrincipal currentUser) {
        if (errors.hasErrors()) throw new IncorrectInputDataException(errors);

        Task task = taskService.updateTask(Long.parseLong(id), taskRequest, currentUser);
        return ModelMapper.mapTaskToTaskResponse(task);
    }

    @DeleteMapping(value = "/tasks/{id}")
    @PreAuthorize("hasRole('USER')")
    public void deleteTask(@PathVariable String id, @CurrentUser UserPrincipal currentUser) {
        taskService.deleteTask(Long.parseLong(id), currentUser);
    }
}
