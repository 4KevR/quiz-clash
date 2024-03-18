package com.github.quizclash.domain;

import java.util.Arrays;

public class QuestionValidator {
  public static QuestionOption[] validate(QuestionOption[] questionOptions) throws InvalidQuestionFormatException {
    if (questionOptions.length != 4) {
      throw new InvalidQuestionFormatException("Question should only have 4 options, got " + questionOptions.length);
    }
    long trueCount = Arrays.stream(questionOptions).filter(QuestionOption::isRight).count();
    if (trueCount != 1) {
      throw new InvalidQuestionFormatException("Only one question option can be right, got " + trueCount);
    }
    return questionOptions;
  }
}
