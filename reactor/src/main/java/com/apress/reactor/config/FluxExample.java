package com.apress.reactor.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.reactor.domain.*;

import java.util.*;

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Configuration
public class FluxExample {

  static private Logger LOG = LoggerFactory.getLogger(FluxExample.class);

  /**
   * EmitterProcessor. In this case this is a processor which is synchronnus that
   * can push data both through user action and by subscribing to an upstream
   * publisher and synchronously draining it. This processor creates a Flux of
   * ToDo instances it provided an implementation of a RingBuffer backend
   * messaging-passing. If you want to use async processor you can use
   * WorkQueueProcessor
   * 
   * 
   * 
   */
  @Bean
  public CommandLineRunner runFluxExample() {
    return args -> {
      EmitterProcessor<ToDo> stream = EmitterProcessor.create();

      Mono<List<ToDo>> promise = stream.filter(s -> s.isCompleted())
          .doOnNext(s -> LOG.info("FLUX >>> ToDo: {}", s.getDescription())).collectList()
          .subscribeOn(Schedulers.single());

      stream.onNext(new ToDo("Read a Book", true));
      stream.onNext(new ToDo("Listen Classical Music", true));
      stream.onNext(new ToDo("Workout in the Mornings"));
      stream.onNext(new ToDo("Organize my room", true));
      stream.onNext(new ToDo("Go to the Car Wash", true));
      stream.onNext(new ToDo("SP1 2018 is coming", true));

      stream.onComplete();
      promise.block();

    };
  }

}
