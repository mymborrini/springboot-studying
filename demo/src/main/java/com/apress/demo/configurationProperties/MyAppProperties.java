package com.apress.demo.configurationProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// The configuration properties tells spring boot that this class is used for
// all the properties defined in the application properties
@ConfigurationProperties(prefix = "myapp")
@Component
public class MyAppProperties {

  private String name;
  private String description;
  private String serverIp;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getServerIp() {
    return serverIp;
  }

  public void setServerIp(String serverIp) {
    this.serverIp = serverIp;
  }

}
