package com.github.quizclash.domain;

public class Question {
  private final int id;
  private final String question;
  private final QuestionOption[] questionOptions;

  public Question(int id, String question, QuestionOption[] questionOptions)throws InvalidQuestionFormatException {
    this.id = id;
    this.question = question;
    this.questionOptions = QuestionValidator.validate(questionOptions);
  }

  public int getId() {
    return id;
  }

  public String getQuestion() {
    return question;
  }

  public QuestionOption[] getQuestionOptions() {
    return questionOptions;
  }

  public boolean checkAnswer(int indexOfAnswer) {
    return questionOptions[indexOfAnswer].isRight();
  }
}
