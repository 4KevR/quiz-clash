package com.github.quizclash.application;

import com.github.quizclash.domain.Actionable;
import com.github.quizclash.domain.Repository;
import com.github.quizclash.domain.Screen;
import com.github.quizclash.domain.ScreenProvider;
import com.github.quizclash.domain.TextInputScreen;
import com.github.quizclash.domain.User;

public class Welcome implements ScreenProvider {
  private final Repository repository;
  private boolean hasNextScreen = true;

  public Welcome(Repository repository) {
    this.repository = repository;
  }

  public Screen fetchScreen() {
    return new TextInputScreen("Your name is required to start the game!", "Enter your name");
  }

  public void submitAction(Actionable<?> action) {
    String actionValue = (String) action.getActionValue();
    User currentUser = new User(actionValue);
    repository.getUserRepository().setUser(currentUser);
    this.hasNextScreen = false;
  }

  public ScreenProvider getNextScreenProvider() {
    return new Menu(repository);
  }

  public boolean hasNextScreen() {
    return hasNextScreen;
  }
}
