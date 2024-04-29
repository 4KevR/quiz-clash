package com.github.quizclash.application.room;

import com.github.quizclash.domain.CategoryRepository;
import com.github.quizclash.domain.User;

import java.util.List;

public abstract class GameRoom
    implements GameRoomActionDispatcher, GameRoomActionSender, GameRoomLifetimeDispatcher {

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

  public abstract String getRoomName();

  public abstract List<User> getPlayers();

  public abstract CategoryRepository getRoomCategoryRepository();
}
