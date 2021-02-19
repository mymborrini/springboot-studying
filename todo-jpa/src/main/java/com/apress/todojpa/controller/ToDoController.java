package com.apress.todojpa.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import com.apress.todojpa.domain.*;
import com.apress.todojpa.repository.ToDoRepository;
import com.apress.todojpa.builder.model.ToDoBuilder;
import com.apress.todojpa.builder.validation.ToDoValidationErrorBuilder;
import com.apress.todojpa.validation.ToDoValidationError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.validation.Errors;

@RestController
@RequestMapping("/api")
public class ToDoController {

  private ToDoRepository repository;

  @Autowired
  public ToDoController(ToDoRepository repository) {
    this.repository = repository;
  }

  /**
   * 
   * ResponseEntity<T> this class returns a full response, including HTTP headers
   * and the body is converted through HttpMessageConverters and written to the
   * HTTP response
   */
  @GetMapping("/todo")
  public ResponseEntity<Iterable<ToDo>> getToDos() {
    return ResponseEntity.ok(repository.findAll());
  }

  @GetMapping("/todo/{id}")
  public ResponseEntity<ToDo> getToDoById(@PathVariable String id) {
    Optional<ToDo> toDo = repository.findById(id);
    if (toDo.isPresent())
      return ResponseEntity.ok(toDo.get());
    return ResponseEntity.notFound().build();
  }

  @PatchMapping("/todo/{id}")
  public ResponseEntity<ToDo> setCompleted(@PathVariable String id) {

    Optional<ToDo> toDo = repository.findById(id);
    if (!toDo.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    ToDo result = toDo.get();
    result.setCompleted(true);
    repository.save(result);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(result.getId()).toUri();

    return ResponseEntity.ok().header("Location", location.toString()).build();

  }

  /**
   * @Valid: This annotation validates incoming data and is used as a method's
   *         parameters. To trigger a validator, it is necessary to annotate the
   *         data you want to validate with @NotNull, @NotBlank and other
   *         annotations. If the validator finds errors they are collected in the
   *         Errors class. You can of course create your custom validator. Then
   *         you can inspect and add the necessary logic to send back error
   *         response
   * 
   */
  @RequestMapping(value = "/todo", method = { RequestMethod.POST, RequestMethod.PUT })
  public ResponseEntity<?> createToDo(@Valid @RequestBody ToDo toDo, Errors errors) {
    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().body(ToDoValidationErrorBuilder.fromBindingErrors(errors));
    }

    ToDo result = repository.save(toDo);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId())
        .toUri();

    return ResponseEntity.created(location).build();

  }

  @DeleteMapping("/todo/{id}")
  public ResponseEntity<ToDo> deleteToDo(@PathVariable String id) {
    repository.delete(ToDoBuilder.create().withId(id).build());
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/todo")
  public ResponseEntity<ToDo> deleteToDo(@RequestBody ToDo toDo) {
    repository.delete(toDo);
    return ResponseEntity.noContent().build();
  }

  /**
   * 
   * @ResponseStatus is normally used when a method haas a void return type. This
   *                 annotation sends back the HttpStatus code
   * @ExceptionHandler: Spring MVC automatically declares build-in resolvers for
   *                    exception and adds the support to this annotation. IN this
   *                    case the ExceptionHandler is declared inside this
   *                    controller class and any exception is redirect to the
   *                    handleException method.
   */
  @ExceptionHandler
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ToDoValidationError handleException(Exception exception) {
    return new ToDoValidationError(exception.getMessage());
  }

}
