package com.github.quizclash.application.screen.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameModeEnumTest {

  @Test
  void getDisplayName() {
    assertEquals("Training", GameModeEnum.TRAINING.getDisplayName());
    assertEquals("Local Multiplayer", GameModeEnum.LOCAL_MULTIPLAYER.getDisplayName());
    assertEquals("Online Multiplayer", GameModeEnum.ONLINE_MULTIPLAYER.getDisplayName());
  }
}