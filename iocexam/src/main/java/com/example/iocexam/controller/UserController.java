package com.example.iocexam.controller;

import com.example.iocexam.domain.User;
import com.example.iocexam.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users/new")
  public String createForm() {
    return "users/createUserForm";
  }

  @PostMapping("/users/new")
  public String create(UserForm form) {
    User user = new User();
    user.setName(form.getName());
    user.setEmail(form.getEmail());
    user.setPassword(form.getPassword());

    userService.joinUser(user);
    return "redirect:/";
  }

  public User getUserByEmail(String email) {
    return userService.getUserByEmail(email);
  }

  @GetMapping("/users")
  public String getUsers(Model model) {
    List<User> users = userService.getUsers();
    model.addAttribute("users", users);
    return "users/userList";
  }
}