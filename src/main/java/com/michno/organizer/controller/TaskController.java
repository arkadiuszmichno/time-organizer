package com.michno.organizer.controller;

import com.michno.organizer.errors.EntityNotFoundException;
import com.michno.organizer.errors.IncorrectInputDataException;
import com.michno.organizer.model.Task;
import com.michno.organizer.model.ToDoList;
import com.michno.organizer.service.TaskService;
import com.michno.organizer.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("*")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    ToDoListService toDoListService;

    @RequestMapping(value = "/task/", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();

        if (tasks.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.GET)
    public Task getTask(@PathVariable("id") String id) {
        Task task = taskService.getTask(Integer.parseInt(id));

        if (task == null) throw new EntityNotFoundException(Task.class.getSimpleName(), Integer.parseInt(id));
        return task;
    }

    @RequestMapping(value = "/list/{listId}/task/", method = RequestMethod.POST)
    public ResponseEntity<Task> addTask(@PathVariable("listId") String id, @RequestBody @Valid Task task, Errors errors, UriComponentsBuilder ucb) {
        if (errors.hasErrors())
            throw new IncorrectInputDataException(errors);

        ToDoList list = toDoListService.getList(Integer.parseInt(id));
        list.addTask(task);
        Task newTask = taskService.createTask(task);

        HttpHeaders headers = new HttpHeaders();
        URI locationURI = ucb.path("/list/")
                .path(String.valueOf(list.getId()))
                .path("/task")
                .path(String.valueOf(newTask.getId()))
                .build()
                .toUri();
        headers.setLocation(locationURI);

        return new ResponseEntity<>(task, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.PUT)
    public Task updateTask(@RequestBody @Valid Task task, Errors errors, @PathVariable String id) {
        if (errors.hasErrors()) throw new IncorrectInputDataException(errors);

        taskService.updateTask(Integer.parseInt(id), task);
        return task;
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.GONE)
    public void deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
    }
}
