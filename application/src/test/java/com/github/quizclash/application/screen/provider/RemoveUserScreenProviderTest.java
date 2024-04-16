package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.OptionScreen;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import com.github.quizclash.domain.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemoveUserScreenProviderTest {
  private static final String SCREEN_NAME = "Which user do you want to remove?";
  private Repository repository;
  private ScreenFactory screenFactory;
  private RemoveUserScreenProvider removeUserScreenProvider;

  @BeforeEach
  void setUp() throws InvalidQuestionFormatException {
    this.repository = Helper.getMockedRepository();
    this.screenFactory = Mockito.mock(ScreenFactory.class);
    this.removeUserScreenProvider = new RemoveUserScreenProvider(this.repository,
        this.screenFactory);
  }

  @Test
  void getNextScreenProviderType() throws InterruptedException {
    OptionScreen optionScreen = Helper.getMockedOptionScreen(1);
    Mockito.when(this.screenFactory.createOptionScreen(SCREEN_NAME,
        this.repository.getUserRepository().getUsers())).thenReturn(optionScreen);
    this.removeUserScreenProvider.execute();
    assertEquals(ScreenProviderType.USER_MENU,
        this.removeUserScreenProvider.getNextScreenProviderType());
  }
}