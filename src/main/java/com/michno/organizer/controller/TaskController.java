package com.michno.organizer.controller;

import com.michno.organizer.model.Task;
import com.michno.organizer.model.ToDoList;
import com.michno.organizer.service.TaskService;
import com.michno.organizer.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Task> getTask(@PathVariable("id") String id) {
        Task task = taskService.getTask(Integer.parseInt(id));

        if (task == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @RequestMapping(value = "/list/{listId}/task/", method = RequestMethod.POST)
    public ResponseEntity<Task> addTask(@PathVariable("listId") String id, @RequestBody Task task) {
        ToDoList list = toDoListService.getList(Integer.parseInt(id));
        list.addTask(task);
        taskService.createTask(task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Task> updateTask(@RequestBody Task task, @PathVariable String id) {
        taskService.updateTask(Integer.parseInt(id), task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
