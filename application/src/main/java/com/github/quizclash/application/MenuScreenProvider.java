package com.github.quizclash.application;

import com.github.quizclash.domain.*;

import java.util.List;

public class MenuScreenProvider implements ScreenProvider, IntegerActionable {
  private final Repository repository;
  private boolean hasNextScreen = true;
  private ScreenProviderType nextScreenProviderType;

  public MenuScreenProvider(Repository repository) {
    this.repository = repository;
  }

  public Screen fetchScreen() {
    String userName = this.repository.getUserRepository().getUsers().get(0).getName();
    String menuTitle = "Hello " + userName + ", select an entry from the menu";
    return new OptionScreen(menuTitle, List.of(MainMenuEnum.values()));
  }

  public void submitAction(Action<Integer> action) {
    int actionValue = action.getActionValue();
    if (actionValue > 0 && actionValue < 5) {
      switch (actionValue) {
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
      this.hasNextScreen = false;
    }
  }

  public ScreenProviderType getNextScreenProviderType() {
    return this.nextScreenProviderType;
  }

  public boolean hasNextScreen() {
    return this.hasNextScreen;
  }
}
