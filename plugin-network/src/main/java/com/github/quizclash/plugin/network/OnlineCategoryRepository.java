package com.github.quizclash.plugin.network;

import com.github.quizclash.adapter.JSONCategoryParser;
import com.github.quizclash.domain.Category;
import com.github.quizclash.domain.CategoryRepository;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import org.json.JSONArray;

import java.util.Arrays;

public class OnlineCategoryRepository implements CategoryRepository {
  private final Category[] categories;

  public OnlineCategoryRepository(JSONArray categories_json) throws InvalidQuestionFormatException {
    this.categories = JSONCategoryParser.parseFromJSONArray(categories_json, false);
  }

  @Override
  public Category[] getCategorySelection(int amountOfCategories) {
    return Arrays.copyOf(categories, amountOfCategories);
  }
}
