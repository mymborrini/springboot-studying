package com.apress.todosimplesecurity.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class ToDo {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @NotNull
  @NotBlank
  private String description;

  @Column(insertable = true, updatable = false)
  private LocalDateTime created;

  private LocalDateTime modified;

  private boolean completed;

  public ToDo() {

  }

  public ToDo(String description) {
    this.description = description;
  }

  @PrePersist
  public void create() {
    this.setCreated(LocalDateTime.now());
    this.setModified(LocalDateTime.now());
  }

  @PreUpdate
  public void update() {
    this.setModified(LocalDateTime.now());
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
