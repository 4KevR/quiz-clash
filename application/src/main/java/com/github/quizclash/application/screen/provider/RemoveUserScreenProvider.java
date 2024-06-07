package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.application.screen.menu.MenuCreator;
import com.github.quizclash.domain.Repository;
import com.github.quizclash.domain.User;

import java.util.List;

public class RemoveUserScreenProvider implements ScreenProvider {
  private final Repository repository;
  private final ScreenFactory screenFactory;
  private ScreenProviderType nextScreenProviderType;

  public RemoveUserScreenProvider(Repository repository, ScreenFactory screenFactory) {
    this.repository = repository;
    this.screenFactory = screenFactory;
  }

  @Override
  public void execute() {
    List<User> users = this.repository.getUserRepository().getUsers();
    MenuCreator menuCreator = new MenuCreator("Which user do you want to remove?",
        users.toArray(new User[0]), screenFactory);
    int userToRemove = menuCreator.displayAndGetSelection();
    this.repository.getUserRepository().removeUser(users.get(userToRemove - 1));
    this.nextScreenProviderType = ScreenProviderType.USER_MENU;
  }

  public ScreenProviderType getNextScreenProviderType() {
    return this.nextScreenProviderType;
  }
}
