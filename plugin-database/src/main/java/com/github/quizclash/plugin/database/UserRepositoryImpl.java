package com.github.quizclash.plugin.database;

import com.github.quizclash.domain.User;
import com.github.quizclash.domain.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
  private List<User> users = new ArrayList<>();

  public List<User> getUsers() {
    return this.users;
  }

  public void addUser(User user) {
    this.users.add(user);
  }

  public void removeUser(User user){
    this.users.remove(user);
  }
}
