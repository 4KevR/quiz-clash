package com.github.quizclash.adapter;

import com.github.quizclash.domain.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONCategoryParser {
  public static Category[] parseFromJSONArray(JSONArray jsonCategories, boolean shuffleQuestions)
      throws InvalidQuestionFormatException {
    Category[] categories = new Category[jsonCategories.length()];
    for (int index = 0; index < jsonCategories.length(); index++) {
      categories[index] = parseCategory(jsonCategories.getJSONObject(index), shuffleQuestions);
    }
    return categories;
  }

  private static Category parseCategory(JSONObject jsonCategory, boolean shuffleQuestions)
      throws InvalidQuestionFormatException {
    CategoryBuilder categoryBuilder = new CategoryBuilder()
        .setId(jsonCategory.getInt("id"))
        .setCategoryName(jsonCategory.getString("categoryName"));

    JSONArray jsonQuestions = jsonCategory.getJSONArray("questions");
    for (int questionIndex = 0; questionIndex < jsonQuestions.length(); questionIndex++) {
      categoryBuilder.addQuestion(
          parseQuestion(jsonQuestions.getJSONObject(questionIndex), shuffleQuestions));
    }
    return categoryBuilder.build();
  }

  private static Question parseQuestion(JSONObject jsonQuestion, boolean shuffleQuestions)
      throws InvalidQuestionFormatException {
    QuestionBuilder questionBuilder = new QuestionBuilder()
        .setId(jsonQuestion.getInt("id"))
        .setQuestion(jsonQuestion.getString("question"))
        .setQuestionShuffling(shuffleQuestions);

    JSONArray jsonQuestionOptions = jsonQuestion.getJSONArray("questionOptions");
    for (int questionOptionIndex = 0; questionOptionIndex < jsonQuestionOptions.length(); questionOptionIndex++) {
      questionBuilder.addQuestionOption(
          parseQuestionOption(jsonQuestionOptions.getJSONObject(questionOptionIndex)));
    }
    return questionBuilder.build();
  }

  private static QuestionOption parseQuestionOption(JSONObject jsonQuestionOption) {
    return new QuestionOptionBuilder()
        .setQuestionOption(jsonQuestionOption.getString("questionOption"))
        .setIsRight(jsonQuestionOption.getBoolean("isRight"))
        .build();
  }
}
