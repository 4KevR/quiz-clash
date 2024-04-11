package com.github.quizclash.domain;

import java.util.List;

public interface UserRepository {
  List<User> getUsers();

  void addUser(User user);

  void removeUser(User user);
}
