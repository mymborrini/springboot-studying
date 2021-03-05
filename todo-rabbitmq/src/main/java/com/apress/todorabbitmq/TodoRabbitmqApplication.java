package com.apress.todorabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The AMQP defines three concepts that are a little different from the JMS
 * world. AMQP defines exchanges, which are entities where the messages are
 * sent. Every exchange takes a message and routes it to zero or more queues.
 * This routing involves an algorithm that is based on the exchange type and
 * rules, called bindings.
 * 
 * The AMQP protocol defines five exchange types: Default, Direct, Fanout,
 * Topic, and Headers. Examples:
 * 
 * The Default exchange: X ------> :routingkey = myQueue ---------> myQueue
 * 
 * The Direct exchange:
 * 
 * X (shopping)
 * 
 * ------> :routingkey = shop.invoice --------> Invoice
 * 
 * ------> :routingkey = shop.delivery --------> Delivery
 * 
 * 
 * The topic exchange:
 * 
 * X (market)
 * 
 * ------> :routingkey = market.us ---------> US
 * 
 * ------> :routingkey = market.eu ---------> Europe
 * 
 * ------> :routingkey = market.* ----------> Everybody
 * 
 * 
 * The headers exchange:
 * 
 * X (market)
 * 
 * -------> x-match = all & market-us = us --------> US
 * 
 * -------> x-match = all & market-eu = eu --------> EU
 * 
 * -------> x-match = any & market-us=us & market-eu=eu ---------> Everybody
 * 
 * 
 * The Fanout exchange:
 * 
 * X (orders)
 * 
 * -------> Inventory
 * 
 * -------> Invoice
 * 
 * -------> Delivery
 * 
 * The main idea is to send a message to an exchange, including a routing key,
 * then the exchange based on its type deliver the message to the queue (or it
 * won't if the routing key does not match)
 * 
 * 
 * 
 */
@SpringBootApplication
public class TodoRabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoRabbitmqApplication.class, args);
	}

}
