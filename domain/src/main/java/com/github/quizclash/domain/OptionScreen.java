package com.github.quizclash.domain;

import java.util.List;

public class OptionScreen extends Screen {
  private final List<Displayable> displayableList;
  public OptionScreen(String screenName, List<Displayable> displayableList) {
    super(screenName);
    this.displayableList = displayableList;
  }

  public List<Displayable> getScreenOptions() {
    return displayableList;
  }
}
