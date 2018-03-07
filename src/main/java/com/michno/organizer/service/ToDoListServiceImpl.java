package com.michno.organizer.service;


import com.michno.organizer.repository.ToDoListDAO;
import com.michno.organizer.model.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoListServiceImpl implements ToDoListService {

    @Autowired
    private ToDoListDAO toDoListDAO;

    @Override
    public List<ToDoList> getAllLists() {
        return toDoListDAO.findAllToDoLists();
    }

    @Override
    public ToDoList getList(int id) {
        return toDoListDAO.findListById(id);
    }

    @Override
    public ToDoList createList(ToDoList list) {
        return toDoListDAO.createList(list);
    }

    @Override
    public void updateList(int id, ToDoList list) {
        toDoListDAO.updateList(id, list);
    }

    @Override
    public void deleteList(int id) {
        toDoListDAO.deleteList(id);
    }

    @Override
    public boolean hasDuplicate(String name) {
        List<ToDoList> lists = toDoListDAO.findListByName(name);
        if (lists.size() == 0) return false;
        return true;
    }
}
