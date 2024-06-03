package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.Helper;
import com.github.quizclash.application.screen.OptionScreen;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.application.screen.displayables.MainMenuEnum;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import com.github.quizclash.domain.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MenuScreenProviderTest {
  private static final String SCREEN_NAME = "Hello Player 1, select an entry from the menu";
  @Mock
  private ScreenFactory screenFactory;
  private MenuScreenProvider menuScreenProvider;

  @BeforeEach
  void setUp() throws InvalidQuestionFormatException {
    Repository repository = Helper.getMockedRepository();
    screenFactory = Mockito.mock(ScreenFactory.class);
    menuScreenProvider = new MenuScreenProvider(repository, screenFactory);
  }

  @Test
  void testGameModeOption() throws InterruptedException {
    executeMenuScreenProvider(1);
    assertEquals(ScreenProviderType.GAME_MODE, menuScreenProvider.getNextScreenProviderType());
  }

  @Test
  void testGameSettingsOption() throws InterruptedException {
    executeMenuScreenProvider(2);
    assertEquals(ScreenProviderType.GAME_SETTINGS, menuScreenProvider.getNextScreenProviderType());
  }

  @Test
  void testUserMenuOption() throws InterruptedException {
    executeMenuScreenProvider(3);
    assertEquals(ScreenProviderType.USER_MENU, menuScreenProvider.getNextScreenProviderType());
  }

  @Test
  void testInvalidOption() throws InterruptedException {
    executeMenuScreenProvider(4);
    assertNull(menuScreenProvider.getNextScreenProviderType());
  }

  void executeMenuScreenProvider(int returnValue) throws InterruptedException {
    OptionScreen optionScreen = Helper.getMockedOptionScreen(returnValue);
    Mockito.when(screenFactory.createOptionScreen(SCREEN_NAME, List.of(MainMenuEnum.values())))
        .thenReturn(optionScreen);
    menuScreenProvider.execute();
  }
}