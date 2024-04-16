package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.OptionScreen;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.application.screen.displayables.UserMenuEnum;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import com.github.quizclash.domain.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMenuScreenProviderTest {
  private static final String SCREEN_NAME = "What do you want to do?";
  private ScreenFactory screenFactory;
  private UserMenuScreenProvider userMenuScreenProvider;

  @BeforeEach
  void setUp() throws InvalidQuestionFormatException {
    Repository repository = Helper.getMockedRepository();
    this.screenFactory = Mockito.mock(ScreenFactory.class);
    this.userMenuScreenProvider = new UserMenuScreenProvider(repository, this.screenFactory);
  }

  @Test
  void testAddUserOption() throws InterruptedException {
    OptionScreen optionScreen = Helper.getMockedOptionScreen(1);
    Mockito.when(this.screenFactory.createOptionScreen(SCREEN_NAME, List.of(UserMenuEnum.values())))
        .thenReturn(optionScreen);
    this.userMenuScreenProvider.execute();
    assertEquals(ScreenProviderType.ADD_USER,
        this.userMenuScreenProvider.getNextScreenProviderType());
  }

  @Test
  void testRemoveUserOption() throws InterruptedException {
    OptionScreen optionScreen = Helper.getMockedOptionScreen(2);
    Mockito.when(this.screenFactory.createOptionScreen(SCREEN_NAME, List.of(UserMenuEnum.values())))
        .thenReturn(optionScreen);
    this.userMenuScreenProvider.execute();
    assertEquals(ScreenProviderType.REMOVE_USER,
        this.userMenuScreenProvider.getNextScreenProviderType());
  }

  @Test
  void testMenuOption() throws InterruptedException {
    OptionScreen optionScreen = Helper.getMockedOptionScreen(3);
    Mockito.when(this.screenFactory.createOptionScreen(SCREEN_NAME, List.of(UserMenuEnum.values())))
        .thenReturn(optionScreen);
    this.userMenuScreenProvider.execute();
    assertEquals(ScreenProviderType.MENU, this.userMenuScreenProvider.getNextScreenProviderType());
  }
}