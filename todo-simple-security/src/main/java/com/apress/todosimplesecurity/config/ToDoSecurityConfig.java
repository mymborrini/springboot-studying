package com.apress.todosimplesecurity.config;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.apress.todosimplesecurity.domain.*;

@Configuration
@EnableConfigurationProperties(ToDoProperties.class)
public class ToDoSecurityConfig extends WebSecurityConfigurerAdapter {

  private final Logger LOG = LoggerFactory.getLogger(ToDoSecurityConfig.class);

  // Use this to connect to the Directory App
  private RestTemplate restTemplate;
  private ToDoProperties toDoProperties;
  private UriComponentsBuilder builder;

  /**
   * This helper class makes a REST call to the directory app endpoint
   */

  public ToDoSecurityConfig(RestTemplateBuilder restTemplateBuilder, ToDoProperties toDoProperties) {
    this.toDoProperties = toDoProperties;
    this.restTemplate = restTemplateBuilder
        .basicAuthentication(toDoProperties.getUsername(), toDoProperties.getPassword()).build();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    System.err.println("ci passa 1");
    auth.userDetailsService(new UserDetailsService() {

      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
          builder = UriComponentsBuilder.fromUriString(toDoProperties.getFindByEmailUri()).queryParam("email",
              username);

          LOG.info("Querying: " + builder.toUriString());
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

    });
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/**").hasRole("USER").and().httpBasic();
  }

}
