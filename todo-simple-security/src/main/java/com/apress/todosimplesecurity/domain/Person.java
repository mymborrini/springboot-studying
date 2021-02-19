package com.apress.todosimplesecurity.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * It's important to mention that calling the directory app returns a more
 * complete JSON object. It must match to do the deserialization (from JSON to
 * object using the Jackson library) but because there is no need for extra
 * information, this class is using the JsonIgnoreProperties annottion that
 * helps match the fields needed. I think this is a nice way to decouple classes
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

  private String email;
  private String password;
  private String role;
  private boolean enabled;

  public String getEmail() {
    return email;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public String getPassword() {
    return password;
  }

  public String getRole() {
    return role;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setRole(String role) {
    this.role = role;
  }

}
