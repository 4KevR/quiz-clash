package com.github.quizclash.application;

import com.github.quizclash.domain.*;

public class WelcomeScreenProvider implements ScreenProvider, StringActionable {
  private final Repository repository;
  private boolean hasNextScreen = true;

  public WelcomeScreenProvider(Repository repository) {
    this.repository = repository;
  }

  public Screen fetchScreen() {
    return new TextInputScreen("Your name is required to start the game!", "Enter your name");
  }

  public void submitAction(Action<String> action) {
    User currentUser = new User(action.getActionValue());
    this.repository.getUserRepository().addUser(currentUser);
    this.hasNextScreen = false;
  }

  public ScreenProviderType getNextScreenProviderType() {
    return ScreenProviderType.MENU;
  }

  public boolean hasNextScreen() {
    return hasNextScreen;
  }
}
