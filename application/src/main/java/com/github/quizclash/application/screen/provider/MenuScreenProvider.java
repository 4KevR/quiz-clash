package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.application.screen.menu.MainMenuEnum;
import com.github.quizclash.application.screen.menu.MenuCreator;
import com.github.quizclash.domain.Repository;

public class MenuScreenProvider implements ScreenProvider {
  private final Repository repository;
  private final ScreenFactory screenFactory;
  private ScreenProviderType nextScreenProviderType;

  public MenuScreenProvider(Repository repository, ScreenFactory screenFactory) {
    this.repository = repository;
    this.screenFactory = screenFactory;
  }

  @Override
  public void execute() {
    String userName = this.repository.getUserRepository().getUsers().get(0).getName();
    String menuTitle = "Hello " + userName + ", select an entry from the menu";
    MenuCreator menuCreator = new MenuCreator(menuTitle, MainMenuEnum.values(), screenFactory);
    int selectedMenuItem = menuCreator.displayAndGetSelection();
    switch (selectedMenuItem) {
      case 1 -> this.nextScreenProviderType = ScreenProviderType.GAME_MODE;
      case 2 -> this.nextScreenProviderType = ScreenProviderType.GAME_SETTINGS;
      case 3 -> this.nextScreenProviderType = ScreenProviderType.USER_MENU;
    }
  }

  public ScreenProviderType getNextScreenProviderType() {
    return this.nextScreenProviderType;
  }
}
