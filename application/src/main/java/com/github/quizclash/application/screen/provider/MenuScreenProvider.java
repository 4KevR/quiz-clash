package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.application.screen.displayables.MainMenuEnum;
import com.github.quizclash.application.screen.OptionScreen;
import com.github.quizclash.domain.*;

import java.util.List;

public class MenuScreenProvider implements ScreenProvider {
  private final Repository repository;
  private final ScreenFactory screenFactory;
  private ScreenProviderType nextScreenProviderType;

  public MenuScreenProvider(Repository repository, ScreenFactory screenFactory) {
    this.repository = repository;
    this.screenFactory = screenFactory;
  }

  @Override
  public void execute() throws InterruptedException {
    int selectedMenuItem = 0;
    while (selectedMenuItem <= 0 || selectedMenuItem > 4) {
      String userName = this.repository.getUserRepository().getUsers().get(0).getName();
      String menuTitle = "Hello " + userName + ", select an entry from the menu";
      OptionScreen optionScreen = screenFactory.createOptionScreen(menuTitle, List.of(MainMenuEnum.values()));
      optionScreen.render();
      selectedMenuItem = optionScreen.getOptionInput().getActionValue();
      switch (selectedMenuItem) {
        case 1:
          this.nextScreenProviderType = ScreenProviderType.GAME_MODE;
          break;
        case 2:
          this.nextScreenProviderType = ScreenProviderType.GAME_SETTINGS;
          break;
        case 3:
          this.nextScreenProviderType = ScreenProviderType.USER_MENU;
          break;
      }
    }
  }

  public ScreenProviderType getNextScreenProviderType() {
    return this.nextScreenProviderType;
  }
}
