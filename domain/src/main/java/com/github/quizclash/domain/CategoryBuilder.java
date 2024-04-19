package com.github.quizclash.domain;

import java.util.ArrayList;
import java.util.List;

public class CategoryBuilder {
  private int id;
  private String categoryName;
  private final List<Question> questions;

  public CategoryBuilder(){
    this.questions = new ArrayList<>();
  }

  public CategoryBuilder setId(int id) {
    this.id = id;
    return this;
  }

  public CategoryBuilder setCategoryName(String categoryName) {
    this.categoryName = categoryName;
    return this;
  }

  public CategoryBuilder addQuestion(Question question) {
    this.questions.add(question);
    return this;
  }

  public Category build() {
    return new Category(id, categoryName, questions.toArray(new Question[0]));
  }
}
