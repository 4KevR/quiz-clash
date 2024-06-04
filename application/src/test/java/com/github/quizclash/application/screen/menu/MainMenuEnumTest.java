package com.github.quizclash.application.screen.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainMenuEnumTest {

  @Test
  void getDisplayName() {
    assertEquals("Start Game", MainMenuEnum.START_GAME.getDisplayName());
    assertEquals("Game Settings", MainMenuEnum.GAME_SETTINGS.getDisplayName());
    assertEquals("User Settings", MainMenuEnum.USER_SETTINGS.getDisplayName());
    assertEquals("Quit", MainMenuEnum.QUIT.getDisplayName());
  }
}