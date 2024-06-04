package com.github.quizclash.application.screen.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMenuEnumTest {

  @Test
  void getDisplayName() {
    assertEquals("Add User", UserMenuEnum.ADD_USER.getDisplayName());
    assertEquals("Remove User", UserMenuEnum.REMOVE_USER.getDisplayName());
    assertEquals("Back to main menu", UserMenuEnum.BACK.getDisplayName());
  }
}