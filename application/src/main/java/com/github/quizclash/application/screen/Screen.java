package com.github.quizclash.application.screen;

public abstract class Screen {
  private final String screenName;

  public Screen(String screenName) {
    this.screenName = screenName;
  }

  public String getScreenName() {
    return screenName;
  }

  public abstract void render();
}
