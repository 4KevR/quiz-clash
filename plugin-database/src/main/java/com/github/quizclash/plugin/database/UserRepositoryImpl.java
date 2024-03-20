package com.github.quizclash.plugin.database;

import com.github.quizclash.domain.User;
import com.github.quizclash.domain.UserRepository;

public class UserRepositoryImpl implements UserRepository {
  private User user;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
