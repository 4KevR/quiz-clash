package com.github.quizclash.domain;

public class QuestionOption {
  private final String questionOption;
  private final boolean isRight;

  public QuestionOption(String questionOption, boolean isRight) {
    this.questionOption = questionOption;
    this.isRight = isRight;
  }

  public String getQuestionOption() {
    return questionOption;
  }

  public boolean isRight() {
    return isRight;
  }
}
