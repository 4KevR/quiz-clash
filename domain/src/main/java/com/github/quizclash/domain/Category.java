package com.github.quizclash.domain;

public class Category {
  private final String categoryName;
  private final Question[] questions;

  public Category(String categoryName, Question[] questions) {
    this.categoryName = categoryName;
    this.questions = questions;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public Question[] getQuestions() {
    return questions;
  }
}
