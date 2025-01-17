package com.example.iocexam.service;

import com.example.iocexam.domain.User;
import com.example.iocexam.repository.UserDao;
import java.util.List;

public class UserServiceImpl implements UserService {
  private final UserDao userDao;

  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void joinUser(User user) {
    userDao.addUser(user);
  }

  @Override
  public User getUserByEmail(String email) {
    return userDao.getUser(email);
  }

  @Override
  public List<User> getUsers() {
    return userDao.getUsers();
  }
}
