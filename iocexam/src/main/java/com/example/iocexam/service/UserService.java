package com.example.iocexam.service;

import com.example.iocexam.domain.User;
import java.util.List;

public interface UserService {
  public void joinUser(User user);
  public User getUserByEmail(String email);
  public List<User> getUsers();
}