package com.example.iocexam.config;

import com.example.iocexam.controller.UserController;
import com.example.iocexam.repository.JpaUseRepository;
import com.example.iocexam.repository.UserRepository;
import com.example.iocexam.service.UserService;
import com.example.iocexam.service.UserServiceImpl;
import jakarta.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

  private final DataSource dataSource;
  private final EntityManager entityManager;

  public UserConfig(DataSource dataSource, EntityManager entityManager) {
    this.dataSource = dataSource;
    this.entityManager = entityManager;
  }

  @Bean
  public UserService userService() {
    return new UserServiceImpl(userRepository());
  }

  @Bean
  public UserRepository userRepository() {
    return new JpaUseRepository(entityManager);
  }
}