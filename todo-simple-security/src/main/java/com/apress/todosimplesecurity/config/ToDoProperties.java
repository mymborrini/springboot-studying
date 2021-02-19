package com.apress.todosimplesecurity.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "todo.authentication")
public class ToDoProperties {

  private String findByEmailUri;
  private String username;
  private String password;

  public String getFindByEmailUri() {
    return findByEmailUri;
  }

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

  public void setFindByEmailUri(String findByEmailUri) {
    this.findByEmailUri = findByEmailUri;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}
