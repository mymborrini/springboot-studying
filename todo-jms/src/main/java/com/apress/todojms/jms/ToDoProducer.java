package com.apress.todojms.jms;

import com.apress.todojms.domain.ToDo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Let's start by introducing the Producer that sends a ToDo to the ActiveMQ
 * broker.
 */
@Component
public class ToDoProducer {

  private static final Logger log = LoggerFactory.getLogger(ToDoProducer.class);

  private JmsTemplate jmsTemplate;

  public ToDoProducer(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  public void sendTo(String destination, ToDo todo) {
    this.jmsTemplate.convertAndSend(destination, todo);
    log.info("Procuder > Message Sent");
  }

}
