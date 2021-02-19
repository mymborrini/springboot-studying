package com.apress.todosimplesecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class ToDoWebConfig implements WebMvcConfigurer {

  /**
   * This is a common way to add a controller for a view inside this web
   * application
   */
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("login");
  }

}
