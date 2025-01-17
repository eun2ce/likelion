package com.example.iocexam.controller;

import com.example.iocexam.domain.User;
import com.example.iocexam.service.UserService;
import java.util.List;

public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  public void joinUser(String name, String email, String password) {
    //실제로 동작할때는 user정보를 사용자로부터 정보를 받겠죠???
    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setPassword(password);

    userService.joinUser(user);
  }

  public User getUserByEmail(String email) {
    return userService.getUserByEmail(email);
  }

  public List<User> getUsers() {
    return userService.getUsers();
  }
}