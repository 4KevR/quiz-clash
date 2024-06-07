package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.application.screen.menu.GameModeEnum;
import com.github.quizclash.application.screen.menu.MenuCreator;
import com.github.quizclash.domain.Repository;

public class GameModeScreenProvider implements ScreenProvider {
  private final ScreenFactory screenFactory;
  private ScreenProviderType nextScreenProviderType;

  public GameModeScreenProvider(Repository repository, ScreenFactory screenFactory) {
    this.screenFactory = screenFactory;
  }

  @Override
  public void execute() {
    MenuCreator menuCreator = new MenuCreator("Select Game Mode", GameModeEnum.values(),
        screenFactory);
    int selection = menuCreator.displayAndGetSelection();
    switch (selection) {
      case 1 -> this.nextScreenProviderType = ScreenProviderType.TRAINING;
      case 2 -> this.nextScreenProviderType = ScreenProviderType.LOCAL_MULTIPLAYER;
      case 3 -> this.nextScreenProviderType = ScreenProviderType.ONLINE_MULTIPLAYER;
    }
  }

  public ScreenProviderType getNextScreenProviderType() {
    return this.nextScreenProviderType;
  }
}
