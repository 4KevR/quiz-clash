package com.github.quizclash.application.screen.menu;

import com.github.quizclash.domain.Displayable;

public enum OnlineMultiplayerMenuEnum implements Displayable {
  CREATE_ROOM("Create a new game room"), JOIN_ROOM("Join to an existing room");

  private final String displayName;

  OnlineMultiplayerMenuEnum(String displayName) {
    this.displayName = displayName;
  }

  @Override
  public String getDisplayName() {
    return displayName;
  }
}
