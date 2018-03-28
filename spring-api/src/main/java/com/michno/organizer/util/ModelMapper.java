package com.michno.organizer.util;


import com.michno.organizer.model.Task;
import com.michno.organizer.model.TodoList;
import com.michno.organizer.payload.TaskRequest;
import com.michno.organizer.payload.TaskResponse;
import com.michno.organizer.payload.TodoListResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ModelMapper {
    public static TodoListResponse mapTodoListToTodoListResponse(TodoList todoList) {
        TodoListResponse todoListResponse = new TodoListResponse();
        todoListResponse.setId(todoList.getId());
        todoListResponse.setName(todoList.getName());

        List<TaskResponse> taskResponses = todoList.getTasks().stream().map(task -> {
            TaskResponse taskResponse = new TaskResponse();
            taskResponse.setId(task.getId());
            taskResponse.setName(task.getName());
            taskResponse.setPriority(task.getPriority());
            taskResponse.setDescription(task.getDescription());
            taskResponse.setCreationDateTime(task.getCreatedAt());
            taskResponse.setEndDate(InstantMapper.mapInstantToString(task.getEndDate()));
            taskResponse.setReached(false);
            return taskResponse;
        }).collect(Collectors.toList());

        todoListResponse.setTasks(taskResponses);

        return todoListResponse;
    }

    public static TaskResponse mapTaskToTaskResponse(Task task) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setName(task.getName());
        taskResponse.setDescription(task.getDescription());
        taskResponse.setPriority(task.getPriority());
        taskResponse.setCreationDateTime(task.getCreatedAt());
        taskResponse.setEndDate(InstantMapper.mapInstantToString(task.getEndDate()));
        taskResponse.setReached(task.isReached());

        return taskResponse;
    }

    public static Task mapTaskRequestToTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setName(taskRequest.getName());
        task.setPriority(taskRequest.getPriority());
        task.setDescription(taskRequest.getDescription());
        task.setEndDate(InstantMapper.mapStringToInstant(taskRequest.getEndDate()));
        task.setReached(false);

        return task;
    }
}
