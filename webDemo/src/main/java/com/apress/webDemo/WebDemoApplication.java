package com.apress.webDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * For the web technology the spring framework has 4 different modules
 * spring-web, spring-webmvc, spring-webflux, spring-websocket
 * 
 * Spring-web module has basic web integration features, servlet listeners and
 * has a web oriented application context
 * 
 * spring-mvc contains all the Spring MVC (Model-View-Controller) and REST
 * service implementations for web applications. The spring-mvc is designed
 * around the DispatcherServlet.class.
 * 
 * The autoconfiguration of spring boot for spring framework add the following
 * features to web application
 * 
 * Static content support, for HTML, CSS, media, javascript
 * 
 * HttpMessageConverters If you are using a regular Spring MVC application and
 * you want to get a JSON response, you need to create the necessary
 * configuration for the HttpMessageConverters bean. Springboot add this support
 * by default so you don't have to do this. This means that you get the JSON
 * format by default
 * 
 * JSON serializer and deserializer, spring boot provides an ease way to create
 * your own serializer, deserializer by extending JsonSerializer<T> and/or
 * JsonDeserializer<T>
 * 
 * Path mathching and content negotiation One of Spring MVC application
 * practices is the ability to respond to any suffix to represent the
 * content-type reponse and it content negotiation. It means if you have
 * something like /api/todo?format=json the content-type is set to
 * application/JSON automatically. It depends only on the name
 * 
 * Error handling Spring boot uses /error mapping to create a white labeled page
 * to show all the global errors
 * 
 * Template engine support, Spring boot supports thymeleaf, and groovy template
 * by autoconfigure it
 * 
 */
@SpringBootApplication
public class WebDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebDemoApplication.class, args);
	}

}
