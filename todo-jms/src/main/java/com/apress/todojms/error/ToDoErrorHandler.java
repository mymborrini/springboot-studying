package com.apress.todojms.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

public class ToDoErrorHandler implements ErrorHandler {

  private static Logger log = LoggerFactory.getLogger(ToDoErrorHandler.class);

  @Override
  public void handleError(Throwable t) {
    // TODO Auto-generated method stub
    log.warn("ToDo error...");
    log.error(t.getCause().getMessage());
  }

}
