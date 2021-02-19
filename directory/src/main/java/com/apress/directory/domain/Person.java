package com.apress.directory.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Person {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @Column(unique = true)
  private String email;

  private String name;

  private String password;

  private String role = "USER";

  private boolean enabled = true;

  private LocalDate birthday;

  @Column(insertable = true, updatable = false)
  private LocalDateTime created;

  private LocalDateTime modified;

  public Person() {

  }

  public Person(String email, String name, String password, String birthday) {
    this.email = email;
    this.name = name;
    this.password = password;
    this.birthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }

  public Person(String email, String name, String password, String birthday, String role, boolean enabled) {
    this();
    this.role = role;
    this.enabled = enabled;
  }

  @PrePersist
  void onCreate() {
    setCreated(LocalDateTime.now());
    setModified(LocalDateTime.now());
  }

  @PreUpdate
  void onModified() {
    setModified(LocalDateTime.now());
  }

  public String getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public String getRole() {
    return role;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public LocalDateTime getModified() {
    return modified;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public void setModified(LocalDateTime modified) {
    this.modified = modified;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

}
