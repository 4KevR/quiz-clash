package com.github.quizclash.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuestionValidatorTest {
  @Test
  void validateForCorrectQuestionOptions() throws InvalidQuestionFormatException {
    QuestionOption[] questionOptions = new QuestionOption[4];
    questionOptions[0] = new QuestionOption("Test 1", true);
    questionOptions[1] = new QuestionOption("Test 2", false);
    questionOptions[2] = new QuestionOption("Test 3", false);
    questionOptions[3] = new QuestionOption("Test 4", false);
    assertEquals(QuestionValidator.validate(questionOptions), questionOptions);
  }

  @Test
  void validateForIncorrectAmountOfQuestionOptions() {
    QuestionOption[] questionOptions = new QuestionOption[5];
    assertThrows(InvalidQuestionFormatException.class,
        () -> QuestionValidator.validate(questionOptions));
  }

  @Test
  void validateForMoreThanOneCorrectAnswer() {
    QuestionOption[] questionOptions = new QuestionOption[4];
    questionOptions[0] = new QuestionOption("Test 1", true);
    questionOptions[1] = new QuestionOption("Test 2", true);
    questionOptions[2] = new QuestionOption("Test 3", false);
    questionOptions[3] = new QuestionOption("Test 4", false);
    assertThrows(InvalidQuestionFormatException.class,
        () -> QuestionValidator.validate(questionOptions));
  }
}
