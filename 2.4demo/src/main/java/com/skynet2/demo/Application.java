package com.skynet2.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import org.springframework.core.metrics.jfr.FlightRecorderApplicationStartup;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Application {

	// setApplicationStartup is a tool of spring which is used to get the metrics
	// if there is a bean which gives you problem, this is really useful for find it out
	/* The FlightRecorderApplicationStartup, this will add a link to java flight recorder which will
	* generate a recording.jfr file, after use the right command:
	* java -XX:StartFlightRecording:filename=recording.jfr,duration=10s -jar target/2.4demo-0.0.1-SNAPSHOT.jar
	* in this case
	*
	* which can be opened by a jdk mission control tool
	* With this I can see if there is a bean which takes a lot of time to be instantiate, like
	* in this case DemoService and DemoController of course
	* */
/*	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplication(Application.class);
		springApplication.setApplicationStartup(new FlightRecorderApplicationStartup());
		springApplication.run(args);
	}*/

	/*
	* Another useful class is the BufferingApplicationStartup, with this I can open an actuator endpoint.
	* So you have to set it into the application.properties. If now i cgo the the browser i have a list of the
	* endpoints actuator.
	*
	* http://localhost:8080/actuator
	*
	* This endpoints helps me to get information about each bean
	* */
	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplication(Application.class);
		springApplication.setApplicationStartup(new BufferingApplicationStartup(1000));
		springApplication.run(args);
	}

}
