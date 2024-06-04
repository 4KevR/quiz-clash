package com.github.quizclash.plugin.database;

import com.github.quizclash.adapter.JSONCategoryParser;
import com.github.quizclash.domain.Category;
import com.github.quizclash.domain.CategoryRepository;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocalCategoryRepository implements CategoryRepository {
  private final Category[] categories;

  public LocalCategoryRepository() throws IOException, InvalidQuestionFormatException {
    JSONArray jsonCategories = JSONResourceReader.from(JSONResourceEnum.CATEGORIES);
    categories = JSONCategoryParser.parseFromJSONArray(jsonCategories, true);
  }

  public Category[] getCategorySelection(int amountOfCategories) {
    final Category[] categoryResult = new Category[amountOfCategories];
    final List<Integer> categoryResultIndexes = new ArrayList<>();
    while (categoryResultIndexes.size() < amountOfCategories) {
      final int newIndex = (int) (Math.random() * categories.length);
      if (!categoryResultIndexes.contains(newIndex)) {
        categoryResult[categoryResultIndexes.size()] = categories[newIndex];
        categoryResultIndexes.add(newIndex);
      }
    }
    return categoryResult;
  }
}
