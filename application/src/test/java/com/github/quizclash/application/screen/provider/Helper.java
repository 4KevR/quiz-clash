package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.action.Action;
import com.github.quizclash.application.screen.NumberInputScreen;
import com.github.quizclash.application.screen.OptionScreen;
import com.github.quizclash.application.screen.TextInputScreen;
import com.github.quizclash.domain.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class Helper {

  public static Question[] QUESTIONS;
  public static Category[] CATEGORIES;

  public static TextInputScreen getMockedTextInputScreen(String returnValue) {
    TextInputScreen textInputScreen = Mockito.mock(TextInputScreen.class);
    Action<String> stringAction = Mockito.mock(Action.class);
    Mockito.when(stringAction.getActionValue()).thenReturn(returnValue);
    Mockito.when(textInputScreen.getTextInput()).thenReturn(stringAction);
    return textInputScreen;
  }

  public static NumberInputScreen getMockedNumberInputScreen(Integer returnValue) {
    NumberInputScreen numberInputScreen = Mockito.mock(NumberInputScreen.class);
    Action<Integer> numberAction = Mockito.mock(Action.class);
    Mockito.when(numberAction.getActionValue()).thenReturn(returnValue);
    Mockito.when(numberInputScreen.getNumberInput()).thenReturn(numberAction);
    return numberInputScreen;
  }

  public static OptionScreen getMockedOptionScreen(Integer returnValue) {
    OptionScreen optionScreen = Mockito.mock(OptionScreen.class);
    Action<Integer> numberAction = Mockito.mock(Action.class);
    Mockito.when(numberAction.getActionValue()).thenReturn(returnValue);
    Mockito.when(optionScreen.getOptionInput()).thenReturn(numberAction);
    return optionScreen;
  }

  public static Repository getMockedRepository() throws InvalidQuestionFormatException {

    QUESTIONS = new Question[]{new Question(1, "Question 1",
        new QuestionOption[]{new QuestionOption("Option 1", true), new QuestionOption("Option 2",
            false), new QuestionOption("Option 3", false), new QuestionOption("Option 4", false)})};

    CATEGORIES = new Category[]{new Category(1, "Geography", QUESTIONS)};

    Repository repository = Mockito.mock(Repository.class);

    CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
    Mockito.when(categoryRepository.getRandomCategories(1)).thenReturn(CATEGORIES);
    Mockito.when(repository.getCategoryRepository()).thenReturn(categoryRepository);

    SettingsRepository settingsRepository = Mockito.mock(SettingsRepository.class);
    Mockito.when(repository.getSettingsRepository()).thenReturn(settingsRepository);

    UserRepository userRepository = Mockito.mock(UserRepository.class);
    List<User> users = new ArrayList<>();
    users.add(new User("Player 1"));
    users.add(new User("Player 2"));
    Mockito.when(userRepository.getUsers()).thenReturn(users);
    Mockito.when(repository.getUserRepository()).thenReturn(userRepository);

    return repository;
  }
}
