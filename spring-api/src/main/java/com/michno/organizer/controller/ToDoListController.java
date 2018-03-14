package com.michno.organizer.controller;

import com.michno.organizer.exception.DuplicateToDoListException;
import com.michno.organizer.exception.IncorrectInputDataException;
import com.michno.organizer.exception.ResourceNotFoundException;
import com.michno.organizer.model.TodoList;
import com.michno.organizer.model.User;
import com.michno.organizer.payload.ApiResponse;
import com.michno.organizer.payload.TaskResponse;
import com.michno.organizer.payload.TodoListRequest;
import com.michno.organizer.payload.TodoListResponse;
import com.michno.organizer.repository.TodoListRepository;
import com.michno.organizer.repository.UserRepository;
import com.michno.organizer.security.CurrentUser;
import com.michno.organizer.security.UserPrincipal;
import com.michno.organizer.service.ToDoListService;
import com.michno.organizer.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lists")
public class ToDoListController {

    @Autowired
    ToDoListService toDoListService;

    @Autowired
    TodoListRepository todoListRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/user/{username}")
    @PreAuthorize("hasRole('USER')")
    public List<TodoListResponse> getListsCreatedBy(@PathVariable String username, @CurrentUser UserPrincipal currentUser) {
        return toDoListService.getListsCreatedBy(username, currentUser);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public TodoListResponse getListById(@CurrentUser UserPrincipal currentUser, @PathVariable("id") Long todoListId) {
        TodoList list = todoListRepository.findTodoListById(todoListId).orElseThrow(() -> new ResourceNotFoundException("TodoList", "id", todoListId));
        if (currentUser != null && list.getCreatedBy() != currentUser.getId()) {
            throw new ResourceNotFoundException("TodoList", "id", todoListId);
        }
        User user = userRepository.findById(list.getCreatedBy())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", list.getCreatedBy()));
        return ModelMapper.mapTodoListToTodoListResponse(list, user);
    }

    @GetMapping(value = "/{id}/tasks")
    @PreAuthorize("hasRole('USER')")
    public List<TaskResponse> getTasks(@PathVariable("id") Long id, @CurrentUser UserPrincipal currentUser) {
        TodoList list = todoListRepository.findOne(id);
        User user = userRepository.findByUsername(currentUser.getUsername()).orElseThrow(() -> new ResourceNotFoundException("User", "username", currentUser.getUsername()));
        TodoListResponse todoListResponse = ModelMapper.mapTodoListToTodoListResponse(list, user);
        return todoListResponse.getTasks();
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addList(@RequestBody @Valid TodoListRequest list, Errors errors, UriComponentsBuilder ucb) {
        if (errors.hasErrors()) {
            throw new IncorrectInputDataException(errors);
        }
        if (toDoListService.hasDuplicate(list.getName()))
            throw new DuplicateToDoListException(list.getName());

        TodoList todoList = new TodoList();
        todoList.setName(list.getName());

        TodoList todoResult = todoListRepository.save(todoList);

        URI locationUri = ucb.path("/list/")
                .path(String.valueOf(todoResult.getId()))
                .build()
                .toUri();


        return ResponseEntity.created(locationUri).body(new ApiResponse(true, "TodoList Created Successfully"));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public TodoListResponse updateList(@RequestBody @Valid TodoList list, Errors errors, @PathVariable Long id, @CurrentUser UserPrincipal currentUser) {
        if (errors.hasErrors()) {
            throw new IncorrectInputDataException(errors);
        }
        todoListRepository.save(list);
        User user = userRepository.findByUsername(currentUser.getUsername()).orElseThrow(() -> new ResourceNotFoundException("User", "username", currentUser.getUsername()));
        return ModelMapper.mapTodoListToTodoListResponse(list, user);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.GONE)
    public void deleteList(@PathVariable Long id) {
        todoListRepository.delete(id);
    }

}
