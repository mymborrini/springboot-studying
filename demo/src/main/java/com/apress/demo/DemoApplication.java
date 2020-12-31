package com.apress.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @SpringBootApplication annotation is equivalent to declaring 
 * @Configuration, @ComponentScan, @EnableAutoConfiguration.
 * If you want you can disable an AutoConfiguration excluding it
 * calling in @SpringbootApplication Exclude since the sprigbootannotation wrap 
 * all three you can call exclude which originally belongs to @EnableAutoConfiguration
 */

@SpringBootApplication(exclude={ActiveMQAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
