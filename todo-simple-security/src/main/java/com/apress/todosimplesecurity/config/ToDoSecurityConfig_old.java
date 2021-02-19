package com.apress.todosimplesecurity.config;

import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;

/**
 * WebSecurityConfigurerAdapter. Extending this class is one way to override
 * security because it allows you to override the methods that you really need.
 * configure(AuthenticationManagerBuilder auth)
 * 
 * AuthenticationManagerBuilder this class is a builder and create an
 * AuthenticationManager that allows you to easily build:
 * 
 * in-memory, LDAP, JDBC authentication
 * 
 * UserDetailsService
 * 
 * AuthenticationProviders
 * 
 * It's also necessary to add a PasswordEncoder and a new and more secure way to
 * use and encrypt/decrypt the password.
 * 
 * BCryptPasswordEncoder In this code you are using the BCryptPasswordEncoder
 * (returns a passwordEncoder implementation) that uses the BCrypt strong
 * hashing function. You can use also Pbkdf2PasswordEncoder (using PBKDF2 with a
 * configurable number of iterations and a random 8-byte random salt value), or
 * SCryptPasswordEncoder(uses the SCrypt hashing function). Even better use
 * DelegatingPasswordEncoder, which supports password upgrades.
 * 
 * This class is the old way. Since it did the configuration by itself. The new
 * Configuration will connect with directory app
 */
// @Configuration
public class ToDoSecurityConfig_old extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    System.err.println("ci passa e non deve");
    auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("apress")
        .password(passwordEncoder().encode("springboot2")).roles("ADMIN", "USER");

  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Spring security allows you to override the default login page in several
   * ways. One way it to configure HttpSecurity. The HttpSecurity class allows you
   * to configure web-based security for specific HTTP requests. By default it is
   * applied to all requests, but can be restricted using requestMatcher or
   * similar methods
   * 
   */
  /*
   * @Override protected void configure(HttpSecurity http) throws Exception {
   * http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
   * }
   */

  /**
   * With httpSecurity override you can also change login page behaviour in case
   * you use mustache for example or themeleaf or jsp. If you finish to configure
   * at logoutSuccessUrl and you try from termonal to curl
   * localhost:8080/api/toDos -u apress:springboot2 it will return an empty line.
   * The main problem is that you are redirect to login page. But there is no way
   * to interact from the command line. In reality that are clients that never use
   * web interfaces. Most of the clients are apps and programatically need to use
   * the REST API, but with this solution there is no way to do authentication to
   * interact with a form. with httpBasic instead you let simple client like curl
   * to to use basic authentication mechanism
   * 
   * In this method miss
   * .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
   * 
   * a way to get css and other static sources without authentication so you can
   * see web app failed since it cannot get the next page after the login
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    System.err.println("ci passa e non deve");
    http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin().loginPage("/login").permitAll().and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").and()
        .httpBasic();

  }

}
