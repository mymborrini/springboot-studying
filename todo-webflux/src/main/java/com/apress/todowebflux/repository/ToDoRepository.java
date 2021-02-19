package com.apress.todowebflux.repository;

import java.util.Arrays;

import com.apress.todowebflux.domain.ToDo;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ToDoRepository {

  private Flux<ToDo> toDoFlux = Flux.fromIterable(Arrays.asList(new ToDo("Do homework"),
      new ToDo("Workout in the mornings", true), new ToDo("Make dinner tonight"), new ToDo("Clean the studio", true)));

  public Mono<ToDo> findById(String id) {
    return Mono.from(toDoFlux.filter(todo -> todo.getId().equals(id)));
  }

  public Flux<ToDo> findAll() {
    return toDoFlux;
  }

}
