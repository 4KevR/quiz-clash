package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.Helper;
import com.github.quizclash.application.screen.OptionScreen;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.application.screen.displayables.GameSettingsEnum;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import com.github.quizclash.domain.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameSettingsScreenProviderTest {
  private static final String SCREEN_NAME = "Which Settings do you want to change?";
  @Mock
  private ScreenFactory screenFactory;
  private GameSettingsScreenProvider gameSettingsScreenProvider;

  @BeforeEach
  void setUp() throws InvalidQuestionFormatException {
    Repository repository = Helper.getMockedRepository();
    screenFactory = Mockito.mock(ScreenFactory.class);
    gameSettingsScreenProvider = new GameSettingsScreenProvider(repository, screenFactory);
  }

  @Test
  void testChangeCategorySettingsOption() throws InterruptedException {
    OptionScreen optionScreen = Helper.getMockedOptionScreen(1);
    Mockito.when(screenFactory.createOptionScreen(SCREEN_NAME, List.of(GameSettingsEnum.values())))
        .thenReturn(optionScreen);
    gameSettingsScreenProvider.execute();
    assertEquals(ScreenProviderType.CHANGE_CATEGORY_SETTINGS,
        gameSettingsScreenProvider.getNextScreenProviderType());
  }

  @Test
  void testMenuOption() throws InterruptedException {
    OptionScreen optionScreen = Helper.getMockedOptionScreen(2);
    Mockito.when(screenFactory.createOptionScreen(SCREEN_NAME, List.of(GameSettingsEnum.values())))
        .thenReturn(optionScreen);
    gameSettingsScreenProvider.execute();
    assertEquals(ScreenProviderType.MENU, gameSettingsScreenProvider.getNextScreenProviderType());
  }
}