package com.apress.todomongo.config;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfig {

  @Value("${spring.data.mongodb.host}")
  private String mongoHost;

  @Value("${spring.data.mongodb.port}")
  private int mongoPort;

  @Value("${spring.data.mongodb.database}")
  private String mongoDB;

  @Value("${spring.data.mongodb.username}")
  private String mongoUser;

  @Value("${spring.data.mongodb.password}")
  private String mongoPsw;

  @Value("${spring.data.mongodb.authentication-database}")
  private String authenticationDatabase;

  @Bean
  public MongoDatabaseFactory mongoDbFactory() {

    ServerAddress serverAddress = new ServerAddress(mongoHost, mongoPort);
    MongoClient mongoClient;

    MongoCredential credential = MongoCredential.createCredential(mongoUser, authenticationDatabase,
        mongoPsw.toCharArray());
    mongoClient = MongoClients.create(MongoClientSettings.builder()
        .applyToSocketSettings(builder -> builder.connectTimeout(60000, TimeUnit.MILLISECONDS))
        .applyToClusterSettings(builder -> builder.hosts(Collections.singletonList(serverAddress)))
        .credential(credential).build());

    return new SimpleMongoClientDatabaseFactory(mongoClient, mongoDB);

  }

  public String getMongoDB() {
    return mongoDB;
  }

  public String getMongoHost() {
    return mongoHost;
  }

  public int getMongoPort() {
    return mongoPort;
  }

  public String getMongoPsw() {
    return mongoPsw;
  }

  public String getMongoUser() {
    return mongoUser;
  }

}
