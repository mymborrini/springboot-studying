package com.apress.directory.repository;

import com.apress.directory.domain.Person;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends CrudRepository<Person, String> {

  /**
   * This syntax tells the Spring Data REST that it needs to implement these
   * methods and create the SQL statement accordingly
   */
  public Person findByEmailIgnoreCase(String email);

}
