package com.github.quizclash.domain;

public class Category implements Displayable {
  private final int id;
  private final String categoryName;
  private final Question[] questions;

  public Category(int id, String categoryName, Question[] questions) {
    this.id = id;
    this.categoryName = categoryName;
    this.questions = questions;
  }

  public int getId() {
    return id;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public Question[] getQuestions() {
    return questions;
  }

  public Question getRandomQuestion() {
    int indexOfRandomQuestion = (int) (Math.random() * questions.length);
    return questions[indexOfRandomQuestion];
  }

  public String getDisplayName() {
    return categoryName;
  }
}
