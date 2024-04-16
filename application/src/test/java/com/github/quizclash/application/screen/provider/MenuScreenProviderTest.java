package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.OptionScreen;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.application.screen.displayables.MainMenuEnum;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import com.github.quizclash.domain.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MenuScreenProviderTest {
  private static final String SCREEN_NAME = "Hello Player 1, select an entry from the menu";
  private ScreenFactory screenFactory;
  private MenuScreenProvider menuScreenProvider;

  @BeforeEach
  void setUp() throws InvalidQuestionFormatException {
    Repository repository = Helper.getMockedRepository();
    this.screenFactory = Mockito.mock(ScreenFactory.class);
    this.menuScreenProvider = new MenuScreenProvider(repository, this.screenFactory);
  }

  @Test
  void testGameModeOption() throws InterruptedException {
    OptionScreen optionScreen = Helper.getMockedOptionScreen(1);
    Mockito.when(this.screenFactory.createOptionScreen(SCREEN_NAME, List.of(MainMenuEnum.values())))
        .thenReturn(optionScreen);
    this.menuScreenProvider.execute();
    assertEquals(ScreenProviderType.GAME_MODE, this.menuScreenProvider.getNextScreenProviderType());
  }

  @Test
  void testGameSettingsOption() throws InterruptedException {
    OptionScreen optionScreen = Helper.getMockedOptionScreen(2);
    Mockito.when(this.screenFactory.createOptionScreen(SCREEN_NAME, List.of(MainMenuEnum.values())))
        .thenReturn(optionScreen);
    this.menuScreenProvider.execute();
    assertEquals(ScreenProviderType.GAME_SETTINGS,
        this.menuScreenProvider.getNextScreenProviderType());
  }

  @Test
  void testUserMenuOption() throws InterruptedException {
    OptionScreen optionScreen = Helper.getMockedOptionScreen(3);
    Mockito.when(this.screenFactory.createOptionScreen(SCREEN_NAME, List.of(MainMenuEnum.values())))
        .thenReturn(optionScreen);
    this.menuScreenProvider.execute();
    assertEquals(ScreenProviderType.USER_MENU, this.menuScreenProvider.getNextScreenProviderType());
  }

  @Test
  void testInvalidOption() throws InterruptedException {
    OptionScreen optionScreen = Helper.getMockedOptionScreen(4);
    Mockito.when(this.screenFactory.createOptionScreen(SCREEN_NAME, List.of(MainMenuEnum.values())))
        .thenReturn(optionScreen);
    this.menuScreenProvider.execute();
    assertNull(this.menuScreenProvider.getNextScreenProviderType());
  }
}