package com.michno.organizer.controller;

import com.michno.organizer.model.Task;
import com.michno.organizer.model.ToDoList;
import com.michno.organizer.service.ToDoListService;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
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
    public ResponseEntity<ToDoList> getList(@PathVariable("id") String id) {
        ToDoList list = toDoListService.getList(Integer.parseInt(id));

        if (list == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/list/{id}/task", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getTasks(@PathVariable("id") String id) {
        ToDoList list = toDoListService.getList(Integer.parseInt(id));
        List<Task> tasks = list.getTasks();
        if (list == null || tasks == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @RequestMapping(value = "/list/", method = RequestMethod.POST)
    public ResponseEntity<ToDoList> addList(@RequestBody ToDoList list) {
        toDoListService.createList(list);
        return new ResponseEntity<ToDoList>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ToDoList> updateList(@RequestBody ToDoList list, @PathVariable String id) {
        toDoListService.updateList(Integer.parseInt(id), list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteList(@PathVariable int id) {
        toDoListService.deleteList(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
