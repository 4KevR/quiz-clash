package com.github.quizclash.application;

import com.github.quizclash.domain.Actionable;
import com.github.quizclash.domain.MainMenuEnum;
import com.github.quizclash.domain.OptionScreen;
import com.github.quizclash.domain.Repository;
import com.github.quizclash.domain.Screen;
import com.github.quizclash.domain.ScreenProvider;
import java.util.List;

public class Menu implements ScreenProvider {
  private final Repository repository;
  private boolean hasNextScreen = true;
  private boolean isEnd = true;

  public Menu(Repository repository) {
    this.repository = repository;
  }

  public Screen fetchScreen() {
    String userName = repository.getUserRepository().getUser().getName();
    String menuTitle = "Hello " + userName + ", select an entry from the menu";
    return new OptionScreen(menuTitle, List.of(MainMenuEnum.values()));
  }

  public void submitAction(Actionable<?> action) {
    int actionValue = (int) action.getActionValue();
    if (actionValue > 0 && actionValue < 3) {
      isEnd = actionValue != 1;
      this.hasNextScreen = false;
    }
  }

  public ScreenProvider getNextScreenProvider() {
    return isEnd ? null : new GameMode(repository);
  }

  public boolean hasNextScreen() {
    return hasNextScreen;
  }
}
