package com.github.quizclash.domain;

public interface CategoryRepository {
  Category[] getCategorySelection(int amountOfCategories);
}
