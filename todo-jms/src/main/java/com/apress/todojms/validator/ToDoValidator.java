package com.apress.todojms.validator;

import com.apress.todojms.domain.ToDo;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ToDoValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return clazz.isAssignableFrom(ToDo.class);
  }

  /**
   * The description field canno be empty
   */
  @Override
  public void validate(Object target, Errors errors) {
    ToDo toDo = (ToDo) target;

    if (toDo == null) {
      errors.reject(null, "ToDo cannot be null");
    } else {
      if (toDo.getDescription() == null || toDo.getDescription().isEmpty()) {
        errors.rejectValue("descritpion", null, "description cannot be null or empty");
      }
    }

  }

}
