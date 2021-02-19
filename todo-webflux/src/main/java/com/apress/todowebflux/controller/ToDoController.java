package com.apress.todowebflux.controller;

import com.apress.todowebflux.repository.ToDoRepository;
import com.apress.todowebflux.domain.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// If you had configured a router there is no need to have a controller
//@RestController
public class ToDoController {

  private ToDoRepository repository;

  public ToDoController(ToDoRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/todo/{id}")
  public Mono<ToDo> getToDo(@PathVariable String id) {

    return this.repository.findById(id);

  }

  @GetMapping("/todo")
  public Flux<ToDo> getToDos() {
    return this.repository.findAll();
  }

}
