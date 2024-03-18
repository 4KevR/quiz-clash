package com.github.quizclash.plugin.database;

import com.github.quizclash.domain.Category;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import com.github.quizclash.domain.Question;
import com.github.quizclash.domain.QuestionOption;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

public class CategoryRepository {
  private final Category[] categories;

  public CategoryRepository() throws IOException, InvalidQuestionFormatException {
    JSONArray jsonCategories = JSONResourceReader.from(JSONResourceEnum.CATEGORIES);
    categories = new Category[jsonCategories.length()];
    for (int index = 0; index < jsonCategories.length(); index++) {
      JSONObject jsonCategory = jsonCategories.getJSONObject(index);
      String categoryName = jsonCategory.getString("categoryName");
      JSONArray jsonQuestions = jsonCategory.getJSONArray("questions");
      Question[] questions = new Question[jsonQuestions.length()];
      for (int questionIndex = 0; questionIndex < jsonQuestions.length(); questionIndex++) {
        JSONObject jsonQuestion = jsonQuestions.getJSONObject(questionIndex);
        String question = jsonQuestion.getString("question");
        JSONArray jsonQuestionOptions = jsonQuestion.getJSONArray("questionOptions");
        QuestionOption[] questionOptions = new QuestionOption[jsonQuestionOptions.length()];
        for (int questionOptionIndex = 0; questionOptionIndex < jsonQuestionOptions.length(); questionOptionIndex++) {
          JSONObject jsonQuestionOption = jsonQuestionOptions.getJSONObject(questionOptionIndex);
          String questionOption = jsonQuestionOption.getString("questionOption");
          boolean isRight = jsonQuestionOption.getBoolean("isRight");
          questionOptions[questionOptionIndex] = new QuestionOption(questionOption, isRight);
        }
        questions[questionIndex] = new Question(question, questionOptions);
      }
      categories[index] = new Category(categoryName, questions);
    }
  }

  public Category[] getCategories() {
    return categories;
  }
}
