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

  public GameModeScreenProvider(Repository repository) {
    this.repository = repository;
  }

  public Screen fetchScreen() {
    return new OptionScreen("Select Game Mode", List.of(GameModeEnum.values()));
  }

  public void submitAction(Actionable<?> action) {
    hasNextScreen = false;
  }

  public ScreenProvider getNextScreenProvider() {
    return new TrainingScreenProvider(repository);
  }

  public boolean hasNextScreen() {
    return hasNextScreen;
  }
}
