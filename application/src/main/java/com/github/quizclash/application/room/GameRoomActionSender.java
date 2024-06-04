package com.github.quizclash.application.room;

public interface GameRoomActionSender {
  void sendSelectedCategoryId(int categoryId);

  void sendSelectedQuestionOptionIndex(int questionOptionIndex);
}
