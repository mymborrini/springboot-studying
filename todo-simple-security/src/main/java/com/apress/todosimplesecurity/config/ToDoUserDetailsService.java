package com.apress.todosimplesecurity.config;

import java.net.URI;

import com.apress.todosimplesecurity.domain.Person;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class ToDoUserDetailsService implements UserDetailsService {

  private RestTemplate restTemplate;
  private ToDoProperties toDoProperties;
  private UriComponentsBuilder builder;

  public ToDoUserDetailsService(RestTemplateBuilder restTemplateBuilder, ToDoProperties toDoProperties) {
    this.toDoProperties = toDoProperties;
    this.restTemplate = restTemplateBuilder
        .basicAuthentication(toDoProperties.getUsername(), toDoProperties.getPassword()).build();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      builder = UriComponentsBuilder.fromUriString(toDoProperties.getFindByEmailUri()).queryParam("email", username);
      ResponseEntity<EntityModel<Person>> responseEntity = restTemplate.exchange(
          RequestEntity.get(URI.create(builder.toUriString())).accept(MediaTypes.HAL_JSON).build(),
          new ParameterizedTypeReference<EntityModel<Person>>() {
          });

      if (responseEntity.getStatusCode() == HttpStatus.OK) {
        EntityModel<Person> resource = responseEntity.getBody();
        Person person = resource.getContent();

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String password = encoder.encode(person.getPassword());

        System.err.println(person.getEmail());
        return User.withUsername(person.getEmail()).accountLocked(!person.isEnabled()).password(password)
            .roles(person.getRole()).build();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    throw new UsernameNotFoundException(username);
  }

}
