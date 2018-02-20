package com.michno.organizer.controller;

import com.michno.organizer.errors.DuplicateToDoListException;
import com.michno.organizer.errors.IncorrectInputDataException;
import com.michno.organizer.errors.EntityNotFoundException;
import com.michno.organizer.model.Task;
import com.michno.organizer.model.ToDoList;
import com.michno.organizer.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("*")
public class ToDoListController {

    @Autowired
    ToDoListService toDoListService;

    @RequestMapping(value = "/list/", method = RequestMethod.GET)
    public ResponseEntity<List<ToDoList>> getAllLists() {
        List<ToDoList> lists = toDoListService.getAllLists();

        if (lists == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(lists, HttpStatus.OK);
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public ToDoList getList(@PathVariable("id") String id) {
        ToDoList list = toDoListService.getList(Integer.parseInt(id));

        if (list == null) throw new EntityNotFoundException(ToDoList.class.getSimpleName(), Integer.parseInt(id));
        return list;
    }

    @RequestMapping(value = "/list/{id}/task", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getTasks(@PathVariable("id") String id) {
        ToDoList list = toDoListService.getList(Integer.parseInt(id));
        List<Task> tasks = list.getTasks();
        if (list == null || tasks == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @RequestMapping(value = "/list/", method = RequestMethod.POST)
    public ResponseEntity<ToDoList> addList(@RequestBody @Valid ToDoList list, Errors errors, UriComponentsBuilder ucb) {
        if (errors.hasErrors()) {
            throw new IncorrectInputDataException(errors);
        }
        if (toDoListService.hasDuplicate(list.getName()))
            throw new DuplicateToDoListException(list.getName());

        ToDoList toDoList = toDoListService.createList(list);

        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/list/")
                .path(String.valueOf(toDoList.getId()))
                .build()
                .toUri();

        headers.setLocation(locationUri);
        return new ResponseEntity<>(list, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.PUT)
    public ToDoList updateList(@RequestBody @Valid ToDoList list, Errors errors, @PathVariable String id) {
        if (errors.hasErrors()) {
            throw new IncorrectInputDataException(errors);
        }
        toDoListService.updateList(Integer.parseInt(id), list);
        return list;
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.GONE)
    public void deleteList(@PathVariable int id) {
        toDoListService.deleteList(id);
    }

}
