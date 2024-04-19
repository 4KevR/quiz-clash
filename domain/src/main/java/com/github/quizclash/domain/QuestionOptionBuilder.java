package com.github.quizclash.domain;

public class QuestionOptionBuilder {
  private String questionOption;
  private boolean isRight;

  public QuestionOptionBuilder setQuestionOption(String questionOption) {
    this.questionOption = questionOption;
    return this;
  }

  public QuestionOptionBuilder setIsRight(boolean isRight) {
    this.isRight = isRight;
    return this;
  }

  public QuestionOption build() {
    return new QuestionOption(questionOption, isRight);
  }
}
