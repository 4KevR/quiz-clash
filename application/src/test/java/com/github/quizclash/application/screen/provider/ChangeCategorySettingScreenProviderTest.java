package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.NumberInputScreen;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import com.github.quizclash.domain.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangeCategorySettingScreenProviderTest {
  private ChangeCategorySettingScreenProvider changeCategorySettingScreenProvider;

  @BeforeEach
  void setUp() throws InvalidQuestionFormatException {
    Repository repository = Helper.getMockedRepository();
    ScreenFactory screenFactory = Mockito.mock(ScreenFactory.class);
    NumberInputScreen numberInputScreen = Helper.getMockedNumberInputScreen(3);
    Mockito.when(screenFactory.createNumberInputScreen(
            "How many categories do you want to play per user in one game?", "Enter a number"))
        .thenReturn(numberInputScreen);
    this.changeCategorySettingScreenProvider = new ChangeCategorySettingScreenProvider(repository,
        screenFactory);
  }

  @Test
  void getNextScreenProviderType() throws InterruptedException {
    this.changeCategorySettingScreenProvider.execute();
    assertEquals(ScreenProviderType.GAME_SETTINGS,
        this.changeCategorySettingScreenProvider.getNextScreenProviderType());
  }
}