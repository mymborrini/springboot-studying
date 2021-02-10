package com.apress.webClientDemo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "todo")
public class ToDoRestClientProperties {

  private String uri;
  private String basePath;

  public String getUri() {
    return uri;
  }

  public String getBasePath() {
    return basePath;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public void setBasePath(String basePath) {
    this.basePath = basePath;
  }

}