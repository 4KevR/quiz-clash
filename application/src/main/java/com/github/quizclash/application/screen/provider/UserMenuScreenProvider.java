package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.application.screen.menu.MenuCreator;
import com.github.quizclash.application.screen.menu.UserMenuEnum;
import com.github.quizclash.domain.Repository;

public class UserMenuScreenProvider implements ScreenProvider {
  private final ScreenFactory screenFactory;
  private ScreenProviderType nextScreenProviderType;

  public UserMenuScreenProvider(Repository repository, ScreenFactory screenFactory) {
    this.screenFactory = screenFactory;
  }

  @Override
  public void execute() {
    MenuCreator menuCreator = new MenuCreator("What do you want to do?", UserMenuEnum.values(),
        screenFactory);
    int selection = menuCreator.displayAndGetSelection();
    switch (selection) {
      case 1 -> this.nextScreenProviderType = ScreenProviderType.ADD_USER;
      case 2 -> this.nextScreenProviderType = ScreenProviderType.REMOVE_USER;
      default -> this.nextScreenProviderType = ScreenProviderType.MENU;
    }
  }

  public ScreenProviderType getNextScreenProviderType() {
    return this.nextScreenProviderType;
  }
}
