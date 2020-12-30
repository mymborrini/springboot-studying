package com.apress.todo.repository;

import com.apress.todo.domain.*;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<ToDo, String>{

}