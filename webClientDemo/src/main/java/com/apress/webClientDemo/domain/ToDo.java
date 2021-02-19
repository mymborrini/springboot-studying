package com.apress.webClientDemo.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;
import lombok.Getter;

@Data
public class ToDo {

  private String id;
  private String description;
  private LocalDateTime created;
  private LocalDateTime modified;
  private boolean completed;

  public ToDo() {
    LocalDateTime date = LocalDateTime.now();
    this.id = UUID.randomUUID().toString();
    this.created = date;
    this.modified = date;
  }

  public ToDo(String description) {
    this();
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public String getDescription() {
    return description;
  }

  public LocalDateTime getModified() {
    return modified;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setModified(LocalDateTime modified) {
    this.modified = modified;
  }

  public boolean isCompleted() {
    return completed;
  }

}
