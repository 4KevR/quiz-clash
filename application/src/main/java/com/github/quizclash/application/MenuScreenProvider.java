package com.github.quizclash.application;

import com.github.quizclash.domain.*;

import java.util.List;

public class MenuScreenProvider implements ScreenProvider {
  private final Repository repository;
  private boolean hasNextScreen = true;
  private ScreenProvider nextScreenProvider;

  public MenuScreenProvider(Repository repository) {
    this.repository = repository;
  }

  public Screen fetchScreen() {
    String userName = this.repository.getUserRepository().getUsers().get(0).getName();
    String menuTitle = "Hello " + userName + ", select an entry from the menu";
    return new OptionScreen(menuTitle, List.of(MainMenuEnum.values()));
  }

  public void submitAction(Actionable<?> action) {
    int actionValue = (int) action.getActionValue();
    if (actionValue > 0 && actionValue < 5) {
      switch (actionValue) {
        case 1:
          this.nextScreenProvider = new GameModeScreenProvider(repository);
          break;
        case 2: 
          this.nextScreenProvider = new GameSettingsScreenProvider(repository);
          break;
        case 3:
          this.nextScreenProvider = new UserMenuScreenProvider(repository);
    	    break;
      }
      this.hasNextScreen = false;
    }
  }

  public ScreenProvider getNextScreenProvider() {
    return this.nextScreenProvider;
  }

  public boolean hasNextScreen() {
    return this.hasNextScreen;
  }
}
