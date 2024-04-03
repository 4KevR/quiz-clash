package com.github.quizclash.domain;

public class User implements Displayable {
  private final String name;

  public User(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getDisplayName() {
    return this.name;
  }
}
