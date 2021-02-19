package com.apress.todoreactivedata.config;

import com.apress.todoreactivedata.domain.ToDo;
import com.apress.todoreactivedata.repository.ToDoRepository;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * EnableReactiveMongoRepositories. This annotation is required to set up all
 * the necessary infrastructure for the reactive stream APIs. It's also
 * important to tell this annotation where the repositories are
 * 
 * AbstractReactiveMongoConfiguration to set up the embedded Mongo, it is
 * necessary to extend from this abstract class and implement the
 * reactiveMongoClient and the getDatabaseName and the reactiveMongoClient
 * methods. the last one creates the MongoClient instance that connects to
 * wherever the port of the embedded MongoDB is set
 * 
 * DependsOn this help by creating the MongoClient after the embeddedMongoServer
 * 
 */

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.apress.todoreactivedata.repository")
public class ToDoConfig extends AbstractReactiveMongoConfiguration {

  private final Environment environment;

  public ToDoConfig(Environment environment) {
    this.environment = environment;
  }

  @Override
  protected String getDatabaseName() {
    return "todos";
  }

  @Override
  @Bean
  @DependsOn("embeddedMongoServer")
  public MongoClient reactiveMongoClient() {
    int port = environment.getProperty("local.mongo.port", Integer.class);
    return MongoClients.create(String.format("mongodb://localhost:%d", port));
  }

  @Bean
  public CommandLineRunner insertAndView(ToDoRepository repository, ApplicationContext context) {
    return args -> {
      repository.save(new ToDo("Do homework")).subscribe();
      repository.save(new ToDo("Workout in the mornings", true)).subscribe();

      repository.save(new ToDo("Make dinner tonight")).subscribe();
      repository.save(new ToDo("Clean the studio", true)).subscribe();

      repository.findAll().subscribe(System.out::println);
    };
  }

}