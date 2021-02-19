package com.apress.todoreactivedata.repository;

import com.apress.todoreactivedata.domain.ToDo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * This interface provides the same Repository functionality that you already
 * know, but now it returns Flux and Mono types
 */
public interface ToDoRepository extends ReactiveMongoRepository<ToDo, String> {

}
