package com.apress.todojms.jms;

import javax.validation.Valid;

import com.apress.todojms.domain.ToDo;
import com.apress.todojms.repository.ToDoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * The consumer class is listening for any incoming message from the ActiveMQ
 * queue
 */
@Component
public class ToDoConsumer {

  private Logger log = LoggerFactory.getLogger(ToDoConsumer.class);

  private ToDoRepository repository;

  public ToDoConsumer(ToDoRepository repository) {
    this.repository = repository;
  }

  @JmsListener(destination = "${todo.jms.destination}", containerFactory = "jmsFactory")
  public void processToDo(@Valid ToDo todo) {
    log.info("Consumer > " + todo);
    log.info("ToDo created > " + repository.save(todo));
  }

}
