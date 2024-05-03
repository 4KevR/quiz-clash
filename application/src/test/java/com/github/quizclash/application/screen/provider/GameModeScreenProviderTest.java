package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.Helper;
import com.github.quizclash.application.screen.OptionScreen;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.application.screen.displayables.GameModeEnum;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import com.github.quizclash.domain.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameModeScreenProviderTest {
  private static final String SCREEN_NAME = "Select Game Mode";
  @Mock
  private ScreenFactory screenFactory;
  private GameModeScreenProvider gameModeScreenProvider;

  @BeforeEach
  void setUp() throws InvalidQuestionFormatException {
    Repository repository = Helper.getMockedRepository();
    screenFactory = Mockito.mock(ScreenFactory.class);
    gameModeScreenProvider = new GameModeScreenProvider(repository, screenFactory);
  }

  @Test
  void testTrainingOption() throws InterruptedException {
    OptionScreen optionScreen = Helper.getMockedOptionScreen(1);
    Mockito.when(screenFactory.createOptionScreen(SCREEN_NAME, List.of(GameModeEnum.values())))
        .thenReturn(optionScreen);
    gameModeScreenProvider.execute();
    assertEquals(ScreenProviderType.TRAINING, gameModeScreenProvider.getNextScreenProviderType());
  }

  @Test
  void testLocalMultiplayerOption() throws InterruptedException {
    OptionScreen optionScreen = Helper.getMockedOptionScreen(2);
    Mockito.when(screenFactory.createOptionScreen(SCREEN_NAME, List.of(GameModeEnum.values())))
        .thenReturn(optionScreen);
    gameModeScreenProvider.execute();
    assertEquals(ScreenProviderType.LOCAL_MULTIPLAYER,
        gameModeScreenProvider.getNextScreenProviderType());
  }
}