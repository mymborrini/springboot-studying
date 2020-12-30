package com.apress.todo.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import java.sql.Timestamp;

@Entity
public class ToDo {

  @Id
  @GeneratedValue(generator="system-uuid")
  @GenericGenerator(name="system-uuid", strategy="uuid")
  private String id;
  private String description;
  private Timestamp created;
  private Timestamp modified;

  private boolean completed;

  public ToDo(){}

  public String getId(){
    return id;
  }

  public String getDescription(){
    return description;
  }

  public Timestamp getCreated(){
    return created;
  }

  public Timestamp getModified(){
    return modified;
  }

  public boolean isCompleted(){
    return completed;
  }

  public void setId(String id){
    this.id = id;
  }

  public void setDescription(String description){
    this.description = description;
  }

  public void setCreated(Timestamp created){
    this.created = created;
  }

  public void setModified(Timestamp modified){
    this.modified = modified;
  }

  public void setCompleted(boolean completed){
    this.completed = completed;
  }



}
