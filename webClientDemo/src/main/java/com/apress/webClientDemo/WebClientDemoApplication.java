package com.apress.webClientDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.apress.webClientDemo.domain.ToDo;

@SpringBootApplication
public class WebClientDemoApplication {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(WebClientDemoApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}

	private Logger log = LoggerFactory.getLogger(WebClientDemoApplication.class);

	@Bean
	public CommandLineRunner process(ToDoRestClient client) {
		return args -> {
			Iterable<ToDo> toDos = client.findAll();
			assert toDos != null;
			toDos.forEach(toDo -> log.info(toDo.toString()));

			ToDo newToDo = client.upsert(new ToDo("Drink plenty of water daily"));
			assert newToDo != null;
			log.info(newToDo.toString());

			ToDo toDo = client.findById(newToDo.getId());
			assert toDo != null;
			log.info(toDo.toString());

			ToDo completed = client.setCompleted(newToDo.getId());
			assert completed.isCompleted();
			log.info(completed.toString());

			client.delete(newToDo.getId());
			assert client.findById(newToDo.getId()) == null;

		};
	}

}
