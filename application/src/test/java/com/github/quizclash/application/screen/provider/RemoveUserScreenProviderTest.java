package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.Helper;
import com.github.quizclash.application.screen.OptionScreen;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import com.github.quizclash.domain.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemoveUserScreenProviderTest {
  private static final String SCREEN_NAME = "Which user do you want to remove?";
  private Repository repository;
  @Mock
  private ScreenFactory screenFactory;
  private RemoveUserScreenProvider removeUserScreenProvider;

  @BeforeEach
  void setUp() throws InvalidQuestionFormatException {
    repository = Helper.getMockedRepository();
    screenFactory = Mockito.mock(ScreenFactory.class);
    removeUserScreenProvider = new RemoveUserScreenProvider(repository, screenFactory);
  }

  @Test
  void getNextScreenProviderType() throws InterruptedException {
    OptionScreen optionScreen = Helper.getMockedOptionScreen(1);
    Mockito
        .when(screenFactory.createOptionScreen(SCREEN_NAME,
            repository.getUserRepository().getUsers()))
        .thenReturn(optionScreen);
    removeUserScreenProvider.execute();
    assertEquals(ScreenProviderType.USER_MENU,
        removeUserScreenProvider.getNextScreenProviderType());
  }
}