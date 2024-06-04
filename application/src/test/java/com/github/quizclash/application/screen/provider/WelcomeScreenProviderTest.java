package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.Helper;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.application.screen.TextInputScreen;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import com.github.quizclash.domain.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WelcomeScreenProviderTest {
  private static final String SCREEN_NAME = "Your name is required to start the game!";
  private static final String INPUT_REQUEST = "Enter your name";
  private WelcomeScreenProvider welcomeScreenProvider;

  @BeforeEach
  void setUp() throws InvalidQuestionFormatException {
    Repository repository = Helper.getMockedRepository();
    ScreenFactory screenFactory = Mockito.mock(ScreenFactory.class);
    TextInputScreen textInputScreen = Helper.getMockedTextInputScreen("Player 1");
    Mockito
        .when(screenFactory.createTextInputScreen(SCREEN_NAME, INPUT_REQUEST))
        .thenReturn(textInputScreen);
    this.welcomeScreenProvider = new WelcomeScreenProvider(repository, screenFactory);
  }

  @Test
  void getNextScreenProviderType() throws InterruptedException {
    this.welcomeScreenProvider.execute();
    assertEquals(ScreenProviderType.MENU, welcomeScreenProvider.getNextScreenProviderType());
  }
}