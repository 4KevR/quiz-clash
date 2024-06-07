package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.application.screen.menu.GameSettingsEnum;
import com.github.quizclash.application.screen.menu.MenuCreator;
import com.github.quizclash.domain.Repository;

public class GameSettingsScreenProvider implements ScreenProvider {
  private final ScreenFactory screenFactory;
  private ScreenProviderType nextScreenProviderType;

  public GameSettingsScreenProvider(Repository repository, ScreenFactory screenFactory) {
    this.screenFactory = screenFactory;
  }

  @Override
  public void execute() {
    MenuCreator menuCreator = new MenuCreator("Which Setting do you want to change?",
        GameSettingsEnum.values(), screenFactory);
    int selection = menuCreator.displayAndGetSelection();
    switch (selection) {
      case 1 -> this.nextScreenProviderType = ScreenProviderType.CHANGE_CATEGORY_SETTINGS;
      default -> this.nextScreenProviderType = ScreenProviderType.MENU;
    }
  }

  public ScreenProviderType getNextScreenProviderType() {
    return this.nextScreenProviderType;
  }
}
