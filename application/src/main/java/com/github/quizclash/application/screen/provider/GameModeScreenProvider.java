package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.OptionScreen;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.application.screen.menu.GameModeEnum;
import com.github.quizclash.domain.Repository;

import java.util.List;

public class GameModeScreenProvider implements ScreenProvider {
  private final Repository repository;
  private final ScreenFactory screenFactory;
  private ScreenProviderType nextScreenProviderType;

  public GameModeScreenProvider(Repository repository, ScreenFactory screenFactory) {
    this.repository = repository;
    this.screenFactory = screenFactory;
  }

  @Override
  public void execute() throws InterruptedException {
    int selection = 0;
    while (selection <= 0 || selection > 4) {
      OptionScreen optionScreen = screenFactory.createOptionScreen("Select Game Mode",
          List.of(GameModeEnum.values()));
      optionScreen.render();
      selection = optionScreen.getOptionInput().getActionValue();
      switch (selection) {
        case 1 -> this.nextScreenProviderType = ScreenProviderType.TRAINING;
        case 2 -> this.nextScreenProviderType = ScreenProviderType.LOCAL_MULTIPLAYER;
        case 3 -> this.nextScreenProviderType = ScreenProviderType.ONLINE_MULTIPLAYER;
      }
    }
  }

  public ScreenProviderType getNextScreenProviderType() {
    return this.nextScreenProviderType;
  }
}
