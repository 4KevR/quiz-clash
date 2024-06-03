package com.github.quizclash.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionTest {
  private static final int QUESTION_ID = 1;
  private static final String QUESTION_STRING = "Question 1";
  private static final QuestionOption[] QUESTION_OPTIONS = new QuestionOption[]{new QuestionOption(
      "Option 1", false), new QuestionOption("Option 2", false), new QuestionOption("Option 3",
      true), new QuestionOption("Option 4", false)};
  private Question question;

  @BeforeEach
  void setUp() throws InvalidQuestionFormatException {
    question = new Question(QUESTION_ID, QUESTION_STRING, QUESTION_OPTIONS);
  }

  @Test
  void testGetId() {
    assertEquals(QUESTION_ID, question.getId());
  }

  @Test
  void testGetQuestion() {
    assertEquals(QuestionTest.QUESTION_STRING, question.getQuestion());
  }

  @Test
  void testGetQuestionOptions() {
    assertArrayEquals(QUESTION_OPTIONS, question.getQuestionOptions());
  }

  @Test
  void testCheckAnswer() {
    QuestionOption[] questionOptions = question.getQuestionOptions();
    for (int i = 0; i < questionOptions.length; i++) {
      assertEquals(questionOptions[i].isRight(), question.checkAnswer(i));
    }
  }
}