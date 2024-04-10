package com.github.quizclash.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question {
  private final int id;
  private final String question;
  private final QuestionOption[] questionOptions;

  public Question(int id, String question, QuestionOption[] questionOptions)throws InvalidQuestionFormatException {
    this.id = id;
    this.question = question;
    List<QuestionOption> options = Arrays.asList(QuestionValidator.validate(questionOptions));
    Collections.shuffle(options);
    this.questionOptions = options.toArray(new QuestionOption[0]);
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
