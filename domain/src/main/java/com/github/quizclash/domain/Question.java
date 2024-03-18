package com.github.quizclash.domain;

public class Question {
  private final String question;
  private final QuestionOption[] questionOptions;

  public Question(String question, QuestionOption[] questionOptions)throws InvalidQuestionFormatException {
    this.question = question;
    this.questionOptions = QuestionValidator.validate(questionOptions);
  }

  public String getQuestion() {
    return question;
  }

  public QuestionOption[] getQuestionOptions() {
    return questionOptions;
  }
}
