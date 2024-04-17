package com.github.quizclash.plugin.database;

import com.github.quizclash.domain.Category;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalCategoryRepositoryTest {
  LocalCategoryRepository categoryRepository = new LocalCategoryRepository();

  LocalCategoryRepositoryTest() throws InvalidQuestionFormatException, IOException {
  }

  @Test
  void testGetRandomCategories() {
    Category[] categories = categoryRepository.getCategorySelection(4);
    assertEquals(categories.length, 4);
  }
}
