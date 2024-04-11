package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.action.Action;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.application.screen.TextInputScreen;
import com.github.quizclash.domain.Repository;
import com.github.quizclash.domain.User;

public class AddUserScreenProvider implements ScreenProvider {
  private final Repository repository;
  private final ScreenFactory screenFactory;
  private ScreenProviderType nextScreenProviderType;

  public AddUserScreenProvider(Repository repository, ScreenFactory screenFactory) {
    this.repository = repository;
    this.screenFactory = screenFactory;
  }

  @Override
  public void execute() throws InterruptedException {
    TextInputScreen textInputScreen = screenFactory.createTextInputScreen("Add another player",
        "Enter name");
    textInputScreen.render();
    Action<String> action = textInputScreen.getTextInput();
    this.repository.getUserRepository().addUser(new User(action.getActionValue()));
    this.nextScreenProviderType = ScreenProviderType.USER_MENU;
  }

  public ScreenProviderType getNextScreenProviderType() {
    return this.nextScreenProviderType;
  }
}
