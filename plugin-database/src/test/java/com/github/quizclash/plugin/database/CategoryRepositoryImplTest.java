package com.github.quizclash.plugin.database;

import com.github.quizclash.domain.Category;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

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
