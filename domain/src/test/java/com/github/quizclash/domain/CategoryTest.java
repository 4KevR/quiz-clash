package com.github.quizclash.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
  private static final int CATEGORY_ID = 1;
  private static final String CATEGORY_NAME = "Category 1";
  private static final QuestionOption[] QUESTION_OPTIONS = {new QuestionOption("Option 1",
      false), new QuestionOption("Option 2", false), new QuestionOption("Option 3",
      true), new QuestionOption("Option 4", false)};
  private Question[] questions;
  private Category category;

  @BeforeEach
  void setUp() throws InvalidQuestionFormatException {
    questions = new Question[]{new Question(1, "Question 1", QUESTION_OPTIONS), new Question(2,
        "Question 2", QUESTION_OPTIONS)};
    category = new Category(CATEGORY_ID, CATEGORY_NAME, questions);
  }

  @Test
  void getId() {
    assertEquals(CATEGORY_ID, category.getId());
  }

  @Test
  void getCategoryName() {
    assertEquals(CATEGORY_NAME, category.getCategoryName());
  }

  @Test
  void getQuestions() {
    assertArrayEquals(questions, category.getQuestions());
  }

  @Test
  void getRandomQuestion() {
    assertTrue(Arrays.asList(questions).contains(category.getRandomQuestion()));
  }

  @Test
  void getDisplayName() {
    assertEquals(CATEGORY_NAME, category.getDisplayName());
  }
}