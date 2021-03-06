package com.michno.organizer.service;


import com.michno.organizer.exception.DuplicateToDoListException;
import com.michno.organizer.exception.ResourceNotFoundException;
import com.michno.organizer.model.TodoList;
import com.michno.organizer.model.User;
import com.michno.organizer.payload.TodoListRequest;
import com.michno.organizer.payload.TodoListResponse;
import com.michno.organizer.repository.TodoListRepository;
import com.michno.organizer.repository.UserRepository;
import com.michno.organizer.security.UserPrincipal;
import com.michno.organizer.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoListService {

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
        List<TodoListResponse> todoListResponses = todoLists.stream().map(todoList -> ModelMapper.mapTodoListToTodoListResponse(todoList)).collect(Collectors.toList());

        return todoListResponses;
    }

    public TodoList findById(Long todoListId) {
        TodoList todoList = todoListRepository.findTodoListById(todoListId).orElseThrow(() -> new ResourceNotFoundException("TodoList", "id", todoListId));

        return todoList;
    }

    public TodoList findByIdAndUser(Long todoListId, UserPrincipal currentUser) {
        TodoList todoList = findById(todoListId);

        if (currentUser != null && todoList.getCreatedBy() != currentUser.getId()) {
            throw new ResourceNotFoundException("TodoList", "id", todoListId);
        }
        return todoList;
    }

    public boolean hasDuplicate(String name, Long userId) {
        Optional<TodoList> list = todoListRepository.findTodoListByNameAndCreatedBy(name, userId);
        if (list.isPresent()) return true;
        else
            return false;
    }

    public TodoList createTodoList(TodoListRequest list, UserPrincipal currentUser) {
        if (hasDuplicate(list.getName(), currentUser.getId()))
            throw new DuplicateToDoListException(list.getName());

        TodoList todoList = new TodoList();
        todoList.setName(list.getName());

        return todoListRepository.save(todoList);
    }

    public TodoList updateTodoList(TodoListRequest list, Long id) {
        TodoList todoList = todoListRepository.findTodoListById(id).orElseThrow(() -> new ResourceNotFoundException("To-do List", "id", id));
        if (todoList.getName() != "Today")
            todoList.setName(list.getName());

        return todoListRepository.save(todoList);

    }

    public void delete(Long id, UserPrincipal currentUser) {
        TodoList list = findByIdAndUser(id, currentUser);
        if (list.getName() != "Today")
            todoListRepository.delete(id);
    }

    public void createTodayList(User resultUser) {
        TodoList list = new TodoList("Today");
        todoListRepository.save(list);
        list.setCreatedBy(resultUser.getId());
        list.setUpdatedBy(resultUser.getId());
        todoListRepository.save(list);
    }

    @Transactional
    public void deleteCreatedBy(Long id) {
        todoListRepository.deleteAllByCreatedBy(id);
    }

    public TodoList findByName(String name) {
        return todoListRepository.findTodoListByName(name).orElseThrow(
                () -> new ResourceNotFoundException("to-do list", "name", name));
    }
}
