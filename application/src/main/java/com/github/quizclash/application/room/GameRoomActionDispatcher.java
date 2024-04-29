package com.github.quizclash.application.room;

public interface GameRoomActionDispatcher {
  void dispatchGameTurnAction();

  void dispatchGameTurnListen();

  void dispatchGameTurnListenCategorySubmission(int selectedCategoryId);

  void dispatchGameTurnListenQuestionOptionSubmission(int selectedQuestionOptionIndex);
}
