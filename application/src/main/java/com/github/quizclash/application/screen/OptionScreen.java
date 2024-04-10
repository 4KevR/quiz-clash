package com.github.quizclash.application.screen;

import com.github.quizclash.application.action.Action;
import com.github.quizclash.domain.Displayable;

import java.util.List;

public abstract class OptionScreen extends Screen {
  private final List<? extends Displayable> displayableList;

  public OptionScreen(String screenName, List<? extends Displayable> displayableList) {
    super(screenName);
    this.displayableList = displayableList;
  }

  public abstract Action<Integer> getOptionInput();

  public List<? extends Displayable> getScreenOptions() {
    return displayableList;
  }
}
