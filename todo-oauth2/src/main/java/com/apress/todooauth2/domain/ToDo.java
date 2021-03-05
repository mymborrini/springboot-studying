package com.apress.todooauth2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * NoArgsConstuctor it creates a class constructor with no arguments
 * 
 * Entity this annotation specifies that the class is an entity and is persisted
 * into the database engine selected
 * 
 * The system-uuid generic generator is based on java UUID and generate a unique
 * 36-charachter ID
 */

@Entity
@Data
@NoArgsConstructor
public class ToDo {

  @NotNull
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  private String id;

  @NotNull
  @NotBlank
  private String description;

  @Column(insertable = true, updatable = false)
  private LocalDateTime created;

  private LocalDateTime modified;

  private boolean completed;

  @PrePersist
  void onCreate() {
    this.setCreated(LocalDateTime.now());
    this.setModified(LocalDateTime.now());
  }

  @PreUpdate
  void onUpdate() {
    this.setModified(LocalDateTime.now());
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public LocalDateTime getModified() {
    return modified;
  }

  public void setModified(LocalDateTime modified) {
    this.modified = modified;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
