package com.github.quizclash.domain;

import com.github.quizclash.domain.Displayable;

public enum MainMenuEnum implements Displayable {
  START_GAME("Start Game"),
  QUIT("Quit");

  private final String displayName;

  MainMenuEnum(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return displayName;
  }
}
