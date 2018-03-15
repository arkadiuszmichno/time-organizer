package com.michno.organizer.service;


import com.michno.organizer.exception.ResourceNotFoundException;
import com.michno.organizer.model.TodoList;
import com.michno.organizer.model.User;
import com.michno.organizer.payload.TodoListResponse;
import com.michno.organizer.repository.TodoListRepository;
import com.michno.organizer.repository.UserRepository;
import com.michno.organizer.security.UserPrincipal;
import com.michno.organizer.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ToDoListService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoListRepository todoListRepository;

    public List<TodoListResponse> getListsCreatedBy(String username, UserPrincipal currentUser) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        List<TodoList> todoLists = todoListRepository.findByCreatedBy(user.getId());

        if (todoLists.isEmpty()) {
            return new ArrayList<>();
        }
        List<TodoListResponse> todoListResponses = todoLists.stream().map(todoList -> ModelMapper.mapTodoListToTodoListResponse(todoList,
                user)).collect(Collectors.toList());

        return todoListResponses;
    }

    public boolean hasDuplicate(String name) {
        Optional<TodoList> list = todoListRepository.findTodoListByName(name);
        if (list.isPresent()) return true;
        else
            return false;
    }
}
