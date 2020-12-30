package com.apress.todo.controller;

import com.apress.todo.domain.ToDo;
import com.apress.todo.repository.ToDoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class ToDoController {

  private final ToDoRepository toDoRepository;

  @Autowired
  public ToDoController(ToDoRepository toDoRepository){
    this.toDoRepository = toDoRepository;
  }

  @GetMapping("")
  public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request){
    modelAndView.setViewName("index");
    return modelAndView;
  }

  @RequestMapping(value="/toDos", method = {RequestMethod.GET}, 
    produces = {
      MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE
    })
  public ResponseEntity<Iterable<ToDo>> getToDos(
    @RequestHeader HttpHeaders headers
  ){ 
     return new ResponseEntity<Iterable<ToDo>>(this.toDoRepository.findAll(), headers, HttpStatus.OK);
  }

}