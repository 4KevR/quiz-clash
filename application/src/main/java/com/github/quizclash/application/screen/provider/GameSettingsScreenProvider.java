package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.OptionScreen;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.application.screen.menu.GameSettingsEnum;
import com.github.quizclash.domain.Repository;

import java.util.List;

public class GameSettingsScreenProvider implements ScreenProvider {
  private final Repository repository;
  private final ScreenFactory screenFactory;
  private ScreenProviderType nextScreenProviderType;

  public GameSettingsScreenProvider(Repository repository, ScreenFactory screenFactory) {
    this.repository = repository;
    this.screenFactory = screenFactory;
  }

  @Override
  public void execute() {
    int selection = 0;
    String menuTitle = "Which Settings do you want to change?";
    while (selection <= 0 || selection > 3) {
      OptionScreen optionScreen = screenFactory.createOptionScreen(menuTitle,
          List.of(GameSettingsEnum.values()));
      optionScreen.render();
      selection = optionScreen.getOptionInput().getActionValue();
      switch (selection) {
        case 1:
          this.nextScreenProviderType = ScreenProviderType.CHANGE_CATEGORY_SETTINGS;
          break;
        default:
          this.nextScreenProviderType = ScreenProviderType.MENU;
          break;
      }
    }
  }

  public ScreenProviderType getNextScreenProviderType() {
    return this.nextScreenProviderType;
  }
}
