package com.github.quizclash.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoreTest {
  private static final int INITIAL_SCORE = 10;
  private Score score;

  @BeforeEach
  void setUp() {
    score = new Score(INITIAL_SCORE);
  }

  @Test
  void getIntScore() {
    assertEquals(INITIAL_SCORE, score.getIntScore());
  }
}