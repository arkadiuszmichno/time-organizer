package com.michno.organizer.repository;


import com.michno.organizer.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
