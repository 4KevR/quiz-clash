package com.github.quizclash.domain;

import java.util.List;

public class OptionScreen extends Screen {
  private final List<? extends Displayable> displayableList;
  public OptionScreen(String screenName, List<? extends Displayable> displayableList) {
    super(screenName);
    this.displayableList = displayableList;
  }

  public List<? extends Displayable> getScreenOptions() {
    return displayableList;
  }
}
