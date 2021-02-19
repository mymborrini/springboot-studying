package com.apress.directory.config;

import com.apress.directory.repository.PersonRepository;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class DirectorySecurityConfig extends WebSecurityConfigurerAdapter {

  private PersonRepository repository;

  public DirectorySecurityConfig(PersonRepository repository) {
    this.repository = repository;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/**").hasRole("ADMIN").and().httpBasic();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.userDetailsService(new DirectoryUserDetailsService(this.repository));
  }

}
