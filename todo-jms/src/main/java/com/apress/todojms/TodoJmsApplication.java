package com.apress.todojms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * JMS stands for Java Messaging Service
 *
 * You can use ActiveMQ for implementing the JMS, or RabbitMQ for implementing
 * AMQP (Advance message queuing protocol) Redis for Pub/Sub, and WebSockets for
 * implementing STOMP (Simple or Streaming Text-Oriented Message Protocol) with
 * Spring boot
 * 
 * Messaging can be synchronous and asynchronous, pub/sub and peer-to-peer, RPC
 * enterprise-based, message broker, ESB (Enterprise service bus), MOM (Message
 * oriented middleware), and so forth.
 * 
 * JMS is an old technology and is java only and it takes a lot of effort to
 * integrate them with other technologys like Spring integration, Google
 * Protobuffers, Apache Thrift, and other technologies to integrate JMS.
 * 
 * 
 * 
 */
@SpringBootApplication
public class TodoJmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoJmsApplication.class, args);
	}

}
