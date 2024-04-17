package com.github.quizclash.application.room;

import com.github.quizclash.domain.CategoryRepository;
import com.github.quizclash.domain.User;

import java.util.List;

public abstract class GameRoom {

  private final User user;
  private final String code;

  public GameRoom(User user, String code) {
    this.user = user;
    this.code = code;
  }

  public String getNameOfPlayingUser() {
    return user.getName();
  }

  public String getCode() {
    return code;
  }

  public abstract void waitForGameToFinish();

  public abstract void addListener(GameRoomListener gameRoomListener);

  public abstract String getRoomName();

  public abstract List<User> getPlayers();

  public abstract CategoryRepository getRoomCategoryRepository();

  public abstract void dispatchRoomJoin();

  public abstract void dispatchPlayerUpdate();

  public abstract void dispatchGameStart();

  public abstract void dispatchGameTurnAction();

  public abstract void dispatchGameTurnListen();

  public abstract void dispatchGameTurnListenCategorySubmission(int selectedCategoryId);

  public abstract void dispatchGameTurnListenQuestionOptionSubmission(int selectedQuestionOptionIndex);

  public abstract void sendSelectedCategoryId(int categoryId);

  public abstract void sendSelectedQuestionOptionIndex(int questionOptionIndex);
}
