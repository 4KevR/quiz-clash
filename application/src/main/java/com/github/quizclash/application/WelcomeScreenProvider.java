package com.github.quizclash.application;

import com.github.quizclash.domain.Actionable;
import com.github.quizclash.domain.Repository;
import com.github.quizclash.domain.Screen;
import com.github.quizclash.domain.ScreenProvider;
import com.github.quizclash.domain.TextInputScreen;
import com.github.quizclash.domain.User;

public class WelcomeScreenProvider implements ScreenProvider {
  private final Repository repository;
  private boolean hasNextScreen = true;

  public WelcomeScreenProvider(Repository repository) {
    this.repository = repository;
  }

  public Screen fetchScreen() {
    return new TextInputScreen("Your name is required to start the game!", "Enter your name");
  }

  public void submitAction(Actionable<?> action) {
    String actionValue = (String) action.getActionValue();
    User currentUser = new User(actionValue);
    this.repository.getUserRepository().addUser(currentUser);
    this.hasNextScreen = false;
  }

  public ScreenProvider getNextScreenProvider() {
    return new MenuScreenProvider(repository);
  }

  public boolean hasNextScreen() {
    return hasNextScreen;
  }
}
