package com.michno.organizer.controller;

import com.michno.organizer.exception.IncorrectInputDataException;
import com.michno.organizer.model.TodoList;
import com.michno.organizer.payload.ApiResponse;
import com.michno.organizer.payload.TaskResponse;
import com.michno.organizer.payload.TodoListRequest;
import com.michno.organizer.payload.TodoListResponse;
import com.michno.organizer.security.CurrentUser;
import com.michno.organizer.security.UserPrincipal;
import com.michno.organizer.service.TodoListService;
import com.michno.organizer.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/lists")
public class ToDoListController {

    @Autowired
    TodoListService todoListService;


    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<TodoListResponse> getListsCreatedBy(@CurrentUser UserPrincipal currentUser) {
        String username = currentUser.getUsername();
        return todoListService.getListsCreatedBy(username, currentUser);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public TodoListResponse getListById(@CurrentUser UserPrincipal currentUser, @PathVariable("id") Long todoListId) {
        TodoList list = todoListService.findByIdAndUser(todoListId, currentUser);

        return ModelMapper.mapTodoListToTodoListResponse(list);
    }

    @GetMapping(value = "/name/{name}")
    @PreAuthorize("hasRole('USER')")
    public TodoListResponse getListByName(@CurrentUser UserPrincipal currentUser, @PathVariable("name") String name) {
        TodoList list = todoListService.findByName(name);
        return ModelMapper.mapTodoListToTodoListResponse(list);
    }

    @GetMapping(value = "/{id}/tasks")
    @PreAuthorize("hasRole('USER')")
    public List<TaskResponse> getTasks(@PathVariable("id") Long id, @CurrentUser UserPrincipal currentUser) {
        TodoList list = todoListService.findByIdAndUser(id, currentUser);

        TodoListResponse todoListResponse = ModelMapper.mapTodoListToTodoListResponse(list);
        return todoListResponse.getTasks();
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addList(@RequestBody @Valid TodoListRequest list, Errors errors, UriComponentsBuilder ucb, @CurrentUser UserPrincipal currentUser) {
        if (errors.hasErrors()) {
            throw new IncorrectInputDataException(errors);
        }

        TodoList todoResult = todoListService.createTodoList(list, currentUser);

        URI locationUri = ucb.path("/list/")
                .path(String.valueOf(todoResult.getId()))
                .build()
                .toUri();

        return ResponseEntity.created(locationUri).body(ModelMapper.mapTodoListToTodoListResponse(todoResult));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public TodoListResponse updateList(@RequestBody @Valid TodoListRequest list, Errors errors, @PathVariable Long id, @CurrentUser UserPrincipal currentUser) {
        if (errors.hasErrors()) {
            throw new IncorrectInputDataException(errors);
        }

        TodoList todoList = todoListService.updateTodoList(list, id);

        return ModelMapper.mapTodoListToTodoListResponse(todoList);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteList(@PathVariable Long id, @CurrentUser UserPrincipal currentUser) {
        todoListService.delete(id, currentUser);
        return ResponseEntity.accepted().body(new ApiResponse(true, "TodoList deleted successfully"));
    }

}
