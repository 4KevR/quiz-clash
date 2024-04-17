package com.github.quizclash.application.screen.menu;

import com.github.quizclash.application.screen.OptionScreen;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.domain.Displayable;

import java.util.List;

public class MenuCreator {
  private final String menuTitle;
  private final Displayable[] menuItems;
  private final ScreenFactory screenFactory;

  public MenuCreator(String menuTitle, Displayable[] menuItems, ScreenFactory screenFactory) {
    this.menuTitle = menuTitle;
    this.menuItems = menuItems;
    this.screenFactory = screenFactory;
  }

  public int displayAndGetSelection() {
    int selection = 0;
    while (selection <= 0 || selection > menuItems.length) {
      OptionScreen optionScreen = screenFactory.createOptionScreen(menuTitle, List.of(menuItems));
      optionScreen.render();
      selection = optionScreen.getOptionInput().getActionValue();
    }
    return selection;
  }
}
