package com.apress.reactor.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoProcessor;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

import com.apress.reactor.domain.*;

@Configuration
public class MonoExample {

  private static Logger LOG = LoggerFactory.getLogger(MonoExample.class);

  /**
   * MonoProcessor. In reactor, there are processors that are a kind of publisher
   * that are also a subscriber. This means that you can subscribe to a processor
   * but also call methods to manually inject data into the sequence or terminate
   * it. In this case, you are using onNext method to emit a ToDoInstance
   * 
   * Mono This is a reactive stream publisher with basic operators that complete
   * successfully by emitting an element or with an error
   * 
   * doOnSuccess This method is called or triggered when the Mono complete
   * successfully
   * 
   * doOnTerminate This method is called when the Mono terminates by completing or
   * successfully or with an error
   * 
   * doOnError This method is called when the Mono types completes with an error
   * 
   * subscribeOn Subscribes to the Mono type and requests unbounded demand on a
   * specified Scheduler work
   * 
   * onNext This methods emites a value
   * 
   * block Subscribers to the Mono type and block until a next signal is received
   * or a timeout or a timout expires
   * 
   */
  @Bean
  public CommandLineRunner runMonoExample() {

    return args -> {
      MonoProcessor<ToDo> promise = MonoProcessor.create();
      Mono<ToDo> result = promise.doOnSuccess(p -> LOG.info("MONO >> ToDo: {}", p.getDescription()))
          .doOnTerminate(() -> LOG.info("MONO >> Done")).doOnError(t -> LOG.error(t.getMessage(), t))
          .subscribeOn(Schedulers.single());

      promise.onNext(new ToDo("Buy my ticket for SpringOne Platform 2018"));

      // promise.onError(new IllegalArgumentException("There is an error processing
      // the ToDo..."));

      result.block(Duration.ofMillis(1000));

    };

  }

}
