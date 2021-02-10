package com.apress.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/* The CommandLineRunner and the ApplicationRunner
* 
*/
@Component
public class CommandLineRunnerExample implements CommandLineRunner {

  public static final Logger log = LoggerFactory.getLogger(CommandLineRunnerExample.class);

  // Args are the arguments passed to the application
  @Override
  public void run(String... args) throws Exception {
    log.info("## > CommandLineRunner Implementation...");
    log.info("Accessing the Info bean: ");

    for (String arg : args) {
      log.info(arg);
    }
  }

}
