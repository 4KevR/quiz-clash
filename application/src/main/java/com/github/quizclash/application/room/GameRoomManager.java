package com.github.quizclash.application.room;

public interface GameRoomManager {
  GameRoom createRoom(String name) throws RoomCreationException, RoomJoinException;

  GameRoom joinRoom(String roomCode) throws RoomJoinException;
}
