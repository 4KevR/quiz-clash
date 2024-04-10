package com.github.quizclash.plugin.database;

import com.github.quizclash.domain.Category;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryRepositoryImplTest {
  CategoryRepositoryImpl categoryRepository = new CategoryRepositoryImpl();

  CategoryRepositoryImplTest() throws InvalidQuestionFormatException, IOException {
  }

  @Test
  void testGetRandomCategories() {
    Category[] categories = categoryRepository.getRandomCategories(4);
    assertEquals(categories.length, 4);
  }
}
