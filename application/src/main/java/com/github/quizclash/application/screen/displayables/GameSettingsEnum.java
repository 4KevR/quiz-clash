package com.github.quizclash.application.screen.displayables;

import com.github.quizclash.domain.Displayable;

public enum GameSettingsEnum implements Displayable {
  CATEGORIES("Categories played per user in one game"), BACK("Back to main menu");

  private final String displayName;

  GameSettingsEnum(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return displayName;
  }
}
