package com.github.quizclash.domain;

public interface CategoryRepository {
  Category[] getCategories();

  Category[] getRandomCategories(int amountOfRandomCategories);
}
