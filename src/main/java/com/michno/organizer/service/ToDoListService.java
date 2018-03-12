package com.michno.organizer.service;

import com.michno.organizer.model.TodoList;
import com.michno.organizer.payload.TodoListResponse;
import com.michno.organizer.security.UserPrincipal;

import java.util.List;

public interface ToDoListService {
    List<TodoListResponse> getListsCreatedBy(String username, UserPrincipal currentUser);

    boolean hasDuplicate(String name);
}
