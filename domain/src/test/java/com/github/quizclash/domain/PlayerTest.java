package com.github.quizclash.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {
  private static final String PLAYER_NAME = "PlayerName";
  private Player player;

  @BeforeEach
  void setUp() {
    player = new Player(PLAYER_NAME);
  }

  @Test
  void getPlayerName() {
    assertEquals(PLAYER_NAME, player.getPlayerName());
  }

  @Test
  void incrementScore() {
    int currentScore = player.getCurrentScore().getIntScore();
    player.incrementScore(new Score(currentScore + 1));
    assertEquals(currentScore + 1, player.getCurrentScore().getIntScore());
  }

  @Test
  void getCurrentScore() {
    assertEquals(0, player.getCurrentScore().getIntScore());
  }
}