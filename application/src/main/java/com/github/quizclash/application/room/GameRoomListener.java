package com.github.quizclash.application.room;

public interface GameRoomListener {
  void onJoinedRoom();

  void onUpdatePlayers();

  void onGameStart();

  void onGameTurnAction();

  void onGameTurnListen();

  void onGameTurnListenCategorySubmission(int selectedCategoryId);

  void onGameTurnListenQuestionOptionSubmission(int selectedQuestionOptionIndex);
}
