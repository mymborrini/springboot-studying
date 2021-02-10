package com.apress.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Spring boot gives you different options for saving your application
 * configuration.
 * 
 * You can use a file name application.properties that should be located in the
 * root classpath of your application
 * 
 * You can use YAML notation file named application.yml that also needs to be
 * located in the root classpath
 * 
 * You can use environment variables. This is becoming the default practice for
 * cloud scenarios
 * 
 * You can use command line arguments
 * 
 * For all this place springboot use a relaxed binding which means that this
 * three are equal: message.destinationName, message.destination-name,
 * MESSAGE_DESTINATION_NAME
 * 
 * If you want to use profiles with application.properties. You have to named it
 * like the following
 * 
 */

@Service
public class ApplicationConfigurationExample {

  // Spring boot inject the property whereever you pass it, in the
  // application.properties
  // or through commandLine
  // java -jar target/myapp.jar --data.server=remoteserver:3030
  @Value("${data.server}")
  private String server;

}
