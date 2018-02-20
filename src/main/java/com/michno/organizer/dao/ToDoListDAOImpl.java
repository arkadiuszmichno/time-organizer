package com.michno.organizer.dao;

import com.michno.organizer.model.ToDoList;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@CrossOrigin("*")
public class ToDoListDAOImpl implements ToDoListDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<ToDoList> findAllToDoLists() {
        Query query = entityManager.createQuery("select to from ToDoList to");
        List<ToDoList> lists = query.getResultList();
        if (lists == null) return new ArrayList<>();
        return lists;
    }

    @Override
    @Transactional
    public ToDoList findListById(int id) {
        return entityManager.find(ToDoList.class, id);
    }

    @Override
    @Transactional
    public ToDoList createList(ToDoList list) {
        return entityManager.merge(list);
    }

    @Override
    @Transactional
    public void updateList(int id, ToDoList list) {
        ToDoList toDolist = findListById(id);
        toDolist.setName(list.getName());
        entityManager.flush();
    }

    @Override
    @Transactional
    public void deleteList(int id) {
        entityManager.remove(findListById(id));
    }

    @Override
    public List<ToDoList> findListByName(String name) {
        Query query = entityManager.createQuery("select t from ToDoList t WHERE t.name= :listName ");
        query.setParameter("listName", name);
        List<ToDoList> lists = query.getResultList();
        return lists;
    }
}
