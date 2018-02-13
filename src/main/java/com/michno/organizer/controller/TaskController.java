package com.michno.organizer.controller;

import com.michno.organizer.model.Task;
import com.michno.organizer.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class TaskController {

    @Autowired
    TaskServiceImpl taskServiceImpl;

    @RequestMapping(value = "/task/", method = RequestMethod.GET)
    public List<Task> getAllTasks() {
        List<Task> tasks = taskServiceImpl.getAllTasks();

        //if (tasks.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return tasks;
        //return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.GET)
    public ResponseEntity<Task> getTask(@PathVariable("id") String id) {
        Task task = taskServiceImpl.getTask(Integer.parseInt(id));

        if (task == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @RequestMapping(value = "/task/", method = RequestMethod.POST)
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        taskServiceImpl.createTask(task);
        return new ResponseEntity<>(task,HttpStatus.OK);
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Task> updateTask(@RequestBody Task task, @PathVariable String id) {
        taskServiceImpl.updateTask(Integer.parseInt(id), task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        taskServiceImpl.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
