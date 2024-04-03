package com.github.quizclash.domain;
public enum MainMenuEnum implements Displayable {
  START_GAME("Start Game"),
  USER_SETTINGS("User Settings"),
  QUIT("Quit");

  private final String displayName;

  MainMenuEnum(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return displayName;
  }
}
