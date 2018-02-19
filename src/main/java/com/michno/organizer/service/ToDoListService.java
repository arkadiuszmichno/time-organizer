package com.michno.organizer.service;

import com.michno.organizer.model.ToDoList;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ToDoListService {
    List<ToDoList> getAllLists();

    ToDoList getList(int i);

    void createList(ToDoList list);

    void updateList(int i, ToDoList list);

    void deleteList(int id);

}
