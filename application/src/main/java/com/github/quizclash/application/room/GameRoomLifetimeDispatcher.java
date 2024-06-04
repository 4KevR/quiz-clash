package com.github.quizclash.application.room;

public interface GameRoomLifetimeDispatcher {

  void waitForGameToFinish();

  void addListener(GameRoomListener gameRoomListener);

  void dispatchRoomJoin();

  void dispatchPlayerUpdate();

  void dispatchGameStart();
}
