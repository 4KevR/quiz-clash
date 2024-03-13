package com.github.quizclash.domain;

public enum GameModeEnum implements Displayable {
  TRAINING("Training"),
  LOCAL_MULTIPLAYER("Local Multiplayer"),
  ONLINE_MULTIPLAYER("Online Multiplayer");

  private final String displayName;

  GameModeEnum(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return displayName;
  }
}
