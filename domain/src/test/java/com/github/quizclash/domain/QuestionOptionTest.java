package com.github.quizclash.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionOptionTest {

  private static final String OPTION_TEXT = "Option 1";
  private static final Boolean IS_RIGHT = true;
  private QuestionOption questionOption;

  @BeforeEach
  void setUp() {
    questionOption = new QuestionOption(OPTION_TEXT, IS_RIGHT);
  }

  @Test
  void getQuestionOption() {
    assertEquals(OPTION_TEXT, questionOption.getQuestionOption());
  }

  @Test
  void isRight() {
    assertEquals(IS_RIGHT, questionOption.isRight());
  }

  @Test
  void getDisplayName() {
    assertEquals(OPTION_TEXT, questionOption.getDisplayName());
  }
}