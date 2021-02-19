package com.apress.reactor.domain;

import java.time.LocalDateTime;

// Continue from page 178
public class ToDo {

  private String id;
  private String description;
  private LocalDateTime created;
  private LocalDateTime modified;
  private boolean completed;

  public ToDo() {

  }

  public ToDo(String description) {
    this();
    this.description = description;
  }

  public ToDo(String description, boolean completed) {
    this();
    this.description = description;
    this.completed = completed;
  }

  public String getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public LocalDateTime getModified() {
    return modified;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public void setModified(LocalDateTime modified) {
    this.modified = modified;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

}
