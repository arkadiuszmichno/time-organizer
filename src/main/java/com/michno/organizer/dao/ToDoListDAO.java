package com.michno.organizer.dao;

import com.michno.organizer.model.ToDoList;

import java.util.List;

public interface ToDoListDAO {
    List<ToDoList> findAllToDoLists();

    ToDoList findListById(int id);

    ToDoList createList(ToDoList list);

    void updateList(int id, ToDoList list);

    void deleteList(int id);

    List<ToDoList> findListByName(String name);
}
