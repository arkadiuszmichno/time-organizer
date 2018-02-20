package com.michno.organizer.dao;

import com.michno.organizer.model.Task;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@CrossOrigin("*")
public class TaskDAOImpl implements TaskDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Task> findAllTasks() {
        Query query = entityManager.createQuery("select t from Task t");
        List<Task> tasks = query.getResultList();
        if (tasks == null) return new ArrayList<>();
        else return tasks;
    }

    @Override
    @Transactional
    public Task findTaskById(int id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    @Transactional
    public Task createTask(Task task) {
        return entityManager.merge(task);
    }

    @Override
    @Transactional
    public void updateTask(int id, Task task) {
        Task t = findTaskById(id);
        t.setName(task.getName());
        t.setDescription(task.getDescription());
        t.setPriority(task.getPriority());
        t.setStartDate(task.getStartDate());
        t.setEndDate(task.getEndDate());

        entityManager.flush();
    }

    @Override
    @Transactional
    public void deleteTask(int id) {
        entityManager.remove(findTaskById(id));
    }
}
