package com.github.quizclash.plugin.database;

import com.github.quizclash.domain.Category;
import com.github.quizclash.domain.CategoryRepository;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import com.github.quizclash.domain.Question;
import com.github.quizclash.domain.QuestionOption;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {
  private final Category[] categories;

  public CategoryRepositoryImpl() throws IOException, InvalidQuestionFormatException {
    JSONArray jsonCategories = JSONResourceReader.from(JSONResourceEnum.CATEGORIES);
    categories = new Category[jsonCategories.length()];
    for (int index = 0; index < jsonCategories.length(); index++) {
      JSONObject jsonCategory = jsonCategories.getJSONObject(index);
      int categoryId = jsonCategory.getInt("id");
      String categoryName = jsonCategory.getString("categoryName");
      JSONArray jsonQuestions = jsonCategory.getJSONArray("questions");
      Question[] questions = new Question[jsonQuestions.length()];
      for (int questionIndex = 0; questionIndex < jsonQuestions.length(); questionIndex++) {
        JSONObject jsonQuestion = jsonQuestions.getJSONObject(questionIndex);
        int questionId = jsonQuestion.getInt("id");
        String question = jsonQuestion.getString("question");
        JSONArray jsonQuestionOptions = jsonQuestion.getJSONArray("questionOptions");
        QuestionOption[] questionOptions = new QuestionOption[jsonQuestionOptions.length()];
        for (int questionOptionIndex = 0; questionOptionIndex < jsonQuestionOptions.length(); questionOptionIndex++) {
          JSONObject jsonQuestionOption = jsonQuestionOptions.getJSONObject(questionOptionIndex);
          String questionOption = jsonQuestionOption.getString("questionOption");
          boolean isRight = jsonQuestionOption.getBoolean("isRight");
          questionOptions[questionOptionIndex] = new QuestionOption(questionOption, isRight);
        }
        questions[questionIndex] = new Question(questionId, question, questionOptions);
      }
      categories[index] = new Category(categoryId, categoryName, questions);
    }
  }

  public Category[] getCategories() {
    return categories;
  }

  public Category[] getRandomCategories(int amountOfRandomCategories) {
    final Category[] categoryResult = new Category[amountOfRandomCategories];
    final List<Integer> categoryResultIndexes = new ArrayList<>();
    while (categoryResultIndexes.size() < amountOfRandomCategories) {
      final int newIndex = (int) (Math.random() * categories.length);
      if (!categoryResultIndexes.contains(newIndex)) {
        categoryResult[categoryResultIndexes.size()] = categories[newIndex];
        categoryResultIndexes.add(newIndex);
      }
    }
    return categoryResult;
  }
}
