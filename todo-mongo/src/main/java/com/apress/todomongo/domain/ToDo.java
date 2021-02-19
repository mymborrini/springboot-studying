package com.apress.todomongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.*;

@Document(collation = "todo")
public class ToDo {

  @NotNull
  @Id
  private String id;

  @NotNull
  @NotBlank
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
