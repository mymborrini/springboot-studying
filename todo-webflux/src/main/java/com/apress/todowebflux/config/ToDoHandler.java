package com.apress.todowebflux.config;

import com.apress.todowebflux.domain.ToDo;
import com.apress.todowebflux.repository.ToDoRepository;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class ToDoHandler {

  private ToDoRepository repository;

  public ToDoHandler(ToDoRepository repository) {
    this.repository = repository;
  }

  /**
   * 
   * Mono<ServerResponse> this response type is used on both methods and it uses
   * the ServerResponse interface with a BodyBuilder fluent API that adds a body
   * to the response. The BodyBuilder interface provides useful methods that can
   * help you build the response, such as adding status with the ok method. You
   * can add headers with the headers methods and so one
   */
  public Mono<ServerResponse> getToDo(ServerRequest request) {
    String toDoId = request.pathVariable("id");
    Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    Mono<ToDo> toDo = this.repository.findById(toDoId);

    return toDo.flatMap(t -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(t)))
        .switchIfEmpty(notFound);
  }

  public Mono<ServerResponse> getToDos(ServerRequest request) {
    Flux<ToDo> toDos = this.repository.findAll();
    return ServerResponse.ok().contentType(APPLICATION_JSON).body(toDos, ToDo.class);
  }

}
