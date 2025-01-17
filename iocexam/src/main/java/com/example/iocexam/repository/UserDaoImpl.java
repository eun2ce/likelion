package com.example.iocexam.repository;

import com.example.iocexam.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
  private final List<User> users = new ArrayList<>();

  @Override
  public User getUser(String email) {
    return users.stream()
        .filter(user -> user.getEmail().equals(email))
        .findFirst()
        .orElse(null);
  }

  @Override
  public List<User> getUsers() {
    return new ArrayList<>(users);
  }

  @Override
  public void addUser(User user) {
    users.add(user);
    System.out.println(user.getName() + "의 정보가 잘 저장되었습니다.");
  }
}
