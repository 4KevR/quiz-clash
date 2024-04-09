package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.displayables.GameModeEnum;
import com.github.quizclash.application.screen.OptionScreen;
import com.github.quizclash.application.screen.Screen;
import com.github.quizclash.application.action.Action;
import com.github.quizclash.application.action.IntegerActionable;
import com.github.quizclash.domain.*;

import java.util.List;

public class GameModeScreenProvider implements ScreenProvider, IntegerActionable {
  private final Repository repository;
  private boolean hasNextScreen = true;
  private ScreenProviderType nextScreenProviderType;

  public GameModeScreenProvider(Repository repository) {
    this.repository = repository;
  }

  public Screen fetchScreen() {
    return new OptionScreen("Select Game Mode", List.of(GameModeEnum.values()));
  }

  public void submitAction(Action<Integer> action) {
    int actionValue = action.getActionValue();
    if (actionValue > 0 && actionValue < 4) {
      switch (actionValue) {
        case 1:
          this.nextScreenProviderType = ScreenProviderType.TRAINING;
          break;
        case 2:
          this.nextScreenProviderType = ScreenProviderType.LOCAL_MULTIPLAYER;
          break;
        case 3:
          // TODO: Implement Multiplayer
          this.nextScreenProviderType = ScreenProviderType.MENU;
          break;
      }
      hasNextScreen = false;
    }
  }

  public ScreenProviderType getNextScreenProviderType() {
    return this.nextScreenProviderType;
  }

  public boolean hasNextScreen() {
    return this.hasNextScreen;
  }
}
