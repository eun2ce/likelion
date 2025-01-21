package com.example.iocexam.repository;

import com.example.iocexam.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

  Optional<User> findByEmail(String email);

  List<User> findAll();

  void save(User user);
}