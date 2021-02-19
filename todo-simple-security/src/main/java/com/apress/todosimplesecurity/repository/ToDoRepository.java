package com.apress.todosimplesecurity.repository;

import com.apress.todosimplesecurity.domain.ToDo;

import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<ToDo, String> {

}
