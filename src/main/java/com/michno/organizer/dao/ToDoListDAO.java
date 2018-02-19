package com.michno.organizer.dao;

import com.michno.organizer.model.ToDoList;

import java.util.List;

public interface ToDoListDAO {
    List<ToDoList> findAllToDoLists();

    ToDoList findListById(int id);

    void createList(ToDoList list);

    void updateList(int id, ToDoList list);

    void deleteList(int id);
}
