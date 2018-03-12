package com.michno.organizer.repository;


import com.michno.organizer.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    List<TodoList> findByCreatedBy(Long userId);

    Optional<TodoList> findTodoListByName(String name);

    Optional<TodoList> findTodoListById(Long id);
}
