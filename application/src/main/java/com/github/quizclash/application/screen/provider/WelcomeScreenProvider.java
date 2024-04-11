package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.action.Action;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.application.screen.TextInputScreen;
import com.github.quizclash.domain.Repository;
import com.github.quizclash.domain.User;

public class WelcomeScreenProvider implements ScreenProvider {
  private final Repository repository;
  private final ScreenFactory screenFactory;

  public WelcomeScreenProvider(Repository repository, ScreenFactory screenFactory) {
    this.repository = repository;
    this.screenFactory = screenFactory;
  }

  @Override
  public void execute() throws InterruptedException {
    TextInputScreen textInputScreen = screenFactory.createTextInputScreen(
        "Your name is required to start the game!", "Enter your name");
    textInputScreen.render();
    Action<String> action = textInputScreen.getTextInput();
    User currentUser = new User(action.getActionValue());
    this.repository.getUserRepository().addUser(currentUser);
  }

  public ScreenProviderType getNextScreenProviderType() {
    return ScreenProviderType.MENU;
  }
}
