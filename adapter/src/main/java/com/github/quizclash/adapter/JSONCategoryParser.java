package com.github.quizclash.adapter;

import com.github.quizclash.domain.Category;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import com.github.quizclash.domain.Question;
import com.github.quizclash.domain.QuestionOption;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONCategoryParser {
  public static Category[] parseFromJSONArray(JSONArray jsonCategories, boolean shuffleQuestions)
      throws InvalidQuestionFormatException {
    Category[] categories = new Category[jsonCategories.length()];
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
        questions[questionIndex] = new Question(questionId, question, questionOptions,
            shuffleQuestions);
      }
      categories[index] = new Category(categoryId, categoryName, questions);
    }
    return categories;
  }
}
