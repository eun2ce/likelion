package com.example.iocexam.repository;

import com.example.iocexam.domain.User;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaUseRepository implements UserRepository {

  private final EntityManager entityManager;

  public JpaUseRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public void save(User user) {
    entityManager.persist(user);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return Optional.ofNullable(entityManager.find(User.class, email));
  }

  @Override
  public List<User> findAll() {
    return entityManager.createQuery("select u from User u", User.class).getResultList();
  }

}
