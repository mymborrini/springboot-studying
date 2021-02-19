package com.apress.directory.config;

import com.apress.directory.domain.Person;
import com.apress.directory.repository.PersonRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This class implements UserDetailsService interface and needs to implement
 * loadUserByUserName and return UserDetails instance. In this implementation,
 * the code is showing how the PersonRepository
 */
public class DirectoryUserDetailsService implements UserDetailsService {

  private PersonRepository repository;

  public DirectoryUserDetailsService(PersonRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      final Person person = this.repository.findByEmailIgnoreCase(username);

      if (person != null) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        String password = encoder.encode(person.getPassword());

        System.err.println(person.getRole());
        return User.withUsername(person.getEmail()).accountLocked(!person.isEnabled()).password(password)
            .roles(person.getRole()).build();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    throw new UsernameNotFoundException(username);
  }

}
