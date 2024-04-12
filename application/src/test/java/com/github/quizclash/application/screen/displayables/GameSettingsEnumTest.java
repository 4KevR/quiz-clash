package com.github.quizclash.application.screen.displayables;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameSettingsEnumTest {

  @Test
  void getDisplayName() {
    assertEquals("Categories played per user in one game",
        GameSettingsEnum.CATEGORIES.getDisplayName());
    assertEquals("Back to main menu", GameSettingsEnum.BACK.getDisplayName());
  }
}