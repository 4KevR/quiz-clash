package com.github.quizclash.application.room;

import com.github.quizclash.domain.User;

public interface GameRoomManager {
  GameRoom createRoom(User user, String name, int amountOfPlayers)
      throws GameRoomCreationException, GameRoomJoinException;

  GameRoom joinRoom(User user, String roomCode) throws GameRoomJoinException;
}
