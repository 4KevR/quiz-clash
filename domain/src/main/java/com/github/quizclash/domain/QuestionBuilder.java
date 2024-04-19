package com.github.quizclash.domain;

import java.util.ArrayList;
import java.util.List;

public class QuestionBuilder {
  private int id;
  private String question;
  private final List<QuestionOption> questionOptions;
  private boolean shuffle = true;

  public QuestionBuilder(){
    this.questionOptions = new ArrayList<>();
  }

  public QuestionBuilder setId(int id) {
    this.id = id;
    return this;
  }

  public QuestionBuilder setQuestion(String question) {
    this.question = question;
    return this;
  }

  public QuestionBuilder addQuestionOption(QuestionOption questionOption) {
    this.questionOptions.add(questionOption);
    return this;
  }

  public QuestionBuilder setQuestionShuffling(boolean shuffle) {
    this.shuffle = shuffle;
    return this;
  }

  public Question build() throws InvalidQuestionFormatException {
    return new Question(id, question, questionOptions.toArray(new QuestionOption[0]), shuffle);
  }
}
