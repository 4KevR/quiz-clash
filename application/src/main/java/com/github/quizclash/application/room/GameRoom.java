package com.github.quizclash.application.room;

public abstract class GameRoom {
  private final String code;

  public GameRoom(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  public abstract String getRoomName();

  public abstract void registerForPlayerUpdates();

  public abstract void setStatusToReady();

  public abstract void registerForGameStart();
}
