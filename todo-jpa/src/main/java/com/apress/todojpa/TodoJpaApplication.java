package com.apress.todojpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * In a simple spring application you are required to use @EnableJpaRepositories
 * annotations that triggers the extra configuration that is applied in the life
 * cycle of the repositories defined within of your application.
 */
@SpringBootApplication
public class TodoJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoJpaApplication.class, args);
	}

}
