package com.apress.todoredis.repository;

import org.springframework.data.repository.CrudRepository;
import com.apress.todoredis.domain.ToDo;

/**
 * It's not necessary to create class or implements anything the spring data jpa
 * does the implmentation for us
 */
public interface ToDoRepository extends CrudRepository<ToDo, String> {

}
