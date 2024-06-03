package com.github.quizclash.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {
  private static final String USERNAME = "UserName";
  private User user;

  @BeforeEach
  void setUp() {
    user = new User(USERNAME);
  }

  @Test
  void getName() {
    assertEquals(USERNAME, user.getName());
  }

  @Test
  void getDisplayName() {
    assertEquals(USERNAME, user.getDisplayName());
  }
}