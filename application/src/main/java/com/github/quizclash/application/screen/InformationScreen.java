package com.github.quizclash.application.screen;

import java.util.List;

public abstract class InformationScreen extends Screen {
  private final List<String> lines;
  private final boolean isBlocking;

  public InformationScreen(String screenName, List<String> lines) {
    super(screenName);
    this.lines = lines;
    this.isBlocking = true;
  }

  public InformationScreen(String screenName, List<String> lines, boolean isBlocking) {
    super(screenName);
    this.lines = lines;
    this.isBlocking = isBlocking;
  }

  public List<String> getLines() {
    return lines;
  }

  public boolean isBlocking() {
    return isBlocking;
  }
}
