package com.michno.organizer.controller;

import com.michno.organizer.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToDoListController {

    @Autowired
    ToDoListService toDoListService;


}
