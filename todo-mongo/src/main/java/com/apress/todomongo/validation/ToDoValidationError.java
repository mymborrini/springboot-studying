package com.apress.todomongo.validation;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.*;

public class ToDoValidationError {

  // Even if the error field is empty it must be included
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private List<String> errors = new ArrayList<>();

  private final String errorMessage;

  public ToDoValidationError(String errorMessage) {
    System.err.println(errorMessage);
    this.errorMessage = errorMessage;
  }

  public void addValidationError(String error) {
    errors.add(error);
  }

  public List<String> getErrors() {
    return errors;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

}