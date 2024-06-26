package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.Helper;
import com.github.quizclash.application.screen.OptionScreen;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.application.screen.menu.UserMenuEnum;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import com.github.quizclash.domain.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMenuScreenProviderTest {
  private static final String SCREEN_NAME = "What do you want to do?";
  @Mock
  private ScreenFactory screenFactory;
  private UserMenuScreenProvider userMenuScreenProvider;

  @BeforeEach
  void setUp() throws InvalidQuestionFormatException {
    Repository repository = Helper.getMockedRepository();
    screenFactory = Mockito.mock(ScreenFactory.class);
    userMenuScreenProvider = new UserMenuScreenProvider(repository, screenFactory);
  }

  @Test
  void testAddUserOption() {
    OptionScreen optionScreen = Helper.getMockedOptionScreen(1);
    Mockito
        .when(screenFactory.createOptionScreen(SCREEN_NAME, List.of(UserMenuEnum.values())))
        .thenReturn(optionScreen);
    userMenuScreenProvider.execute();
    assertEquals(ScreenProviderType.ADD_USER, userMenuScreenProvider.getNextScreenProviderType());
  }

  @Test
  void testRemoveUserOption() {
    OptionScreen optionScreen = Helper.getMockedOptionScreen(2);
    Mockito
        .when(screenFactory.createOptionScreen(SCREEN_NAME, List.of(UserMenuEnum.values())))
        .thenReturn(optionScreen);
    userMenuScreenProvider.execute();
    assertEquals(ScreenProviderType.REMOVE_USER,
        userMenuScreenProvider.getNextScreenProviderType());
  }

  @Test
  void testMenuOption() {
    OptionScreen optionScreen = Helper.getMockedOptionScreen(3);
    Mockito
        .when(screenFactory.createOptionScreen(SCREEN_NAME, List.of(UserMenuEnum.values())))
        .thenReturn(optionScreen);
    userMenuScreenProvider.execute();
    assertEquals(ScreenProviderType.MENU, userMenuScreenProvider.getNextScreenProviderType());
  }
}