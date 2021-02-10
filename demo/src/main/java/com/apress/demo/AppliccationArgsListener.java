package com.apress.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationArguments;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class AppliccationArgsListener {

  private static final Logger log = LoggerFactory.getLogger(AppliccationArgsListener.class);

  // As you can see I can pass the applicationArgumnets to the constructor
  // The Application Argumentents are the arguments I pass to
  @Autowired
  public AppliccationArgsListener(ApplicationArguments args) {

    // ./mvnw spring-boot:run -Dspring-boot.run.arguments="--enable"
    boolean enable = args.containsOption("enable");
    if (enable) {
      log.info("## > You are enabled!");
    }

    List<String> _args = args.getNonOptionArgs();

    // ./mvnw spring-boot:run -Dspring-boot.run.arguments="arg1,arg2"
    if (!_args.isEmpty()) {
      log.info("## > extra args....");
      _args.forEach(file -> log.info(file));
    }

  }

}