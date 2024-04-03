package com.github.quizclash.application;

import com.github.quizclash.domain.Actionable;
import com.github.quizclash.domain.GameModeEnum;
import com.github.quizclash.domain.OptionScreen;
import com.github.quizclash.domain.Repository;
import com.github.quizclash.domain.Screen;
import com.github.quizclash.domain.ScreenProvider;
import java.util.List;

public class GameModeScreenProvider implements ScreenProvider {
  private final Repository repository;
  private boolean hasNextScreen = true;
  private ScreenProvider nextScreenProvider;

  public GameModeScreenProvider(Repository repository) {
    this.repository = repository;
  }

  public Screen fetchScreen() {
    return new OptionScreen("Select Game Mode", List.of(GameModeEnum.values()));
  }

  public void submitAction(Actionable<?> action) {
    int actionValue = (int) action.getActionValue();
    if (actionValue > 0 && actionValue < 4) {
      switch (actionValue) {
        case 1:
          this.nextScreenProvider = new TrainingScreenProvider(repository);
          break;
        case 2:
          this.nextScreenProvider = new LocalMultiplayerScreenProvider(repository);
          break;
        case 3:
          // TODO: Implement Multiplayer
          this.nextScreenProvider = new MenuScreenProvider(repository);
          break;
      }
      hasNextScreen = false;
    }
  }

  public ScreenProvider getNextScreenProvider() {
    return this.nextScreenProvider;
  }

  public boolean hasNextScreen() {
    return this.hasNextScreen;
  }
}
