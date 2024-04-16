package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.application.screen.TextInputScreen;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import com.github.quizclash.domain.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddUserScreenProviderTest {
  private AddUserScreenProvider addUserScreenProvider;

  @BeforeEach
  void setUp() throws InvalidQuestionFormatException {
    Repository repository = Helper.getMockedRepository();
    ScreenFactory screenFactory = Mockito.mock(ScreenFactory.class);
    TextInputScreen textInputScreen = Helper.getMockedTextInputScreen("Player 1");
    Mockito.when(screenFactory.createTextInputScreen("Add another player", "Enter name"))
        .thenReturn(textInputScreen);
    this.addUserScreenProvider = new AddUserScreenProvider(repository, screenFactory);
  }

  @Test
  void getNextScreenProviderType() throws InterruptedException {
    this.addUserScreenProvider.execute();
    assertEquals(ScreenProviderType.USER_MENU,
        this.addUserScreenProvider.getNextScreenProviderType());
  }
}