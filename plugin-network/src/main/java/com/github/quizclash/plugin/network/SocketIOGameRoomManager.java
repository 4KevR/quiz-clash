package com.github.quizclash.plugin.network;

import com.github.quizclash.application.room.GameRoom;
import com.github.quizclash.application.room.GameRoomManager;
import com.github.quizclash.application.room.RoomCreationException;
import com.github.quizclash.domain.User;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SocketIOGameRoomManager implements GameRoomManager {
  private final URI gameServerURL;

  public SocketIOGameRoomManager(URI gameServerURL) {
    this.gameServerURL = gameServerURL;
  }

  @Override
  public GameRoom createRoom(User user, String name, int amountOfPlayers)
      throws RoomCreationException {
    JSONObject createRoomBody = new JSONObject();
    createRoomBody.put("room_name", name);
    createRoomBody.put("amount_of_players", amountOfPlayers);
    HttpRequest createRoomRequest = HttpRequest.newBuilder()
        .header("Content-Type", "application/json")
        .uri(URI.create(gameServerURL.toString() + "/room"))
        .POST(HttpRequest.BodyPublishers.ofString(createRoomBody.toString()))
        .build();
    try {
      HttpResponse<String> response = HttpClient.newBuilder()
          .build()
          .send(createRoomRequest, HttpResponse.BodyHandlers.ofString());
      String newRoomCode = response.body();
      if (response.statusCode() != 201) throw new RoomCreationException("Could not create room");
      return new SocketIOGameRoom(user, gameServerURL, newRoomCode);
    } catch (IOException | InterruptedException e) {
      throw new RoomCreationException("Could not create room - " + e);
    }
  }

  @Override
  public GameRoom joinRoom(User user, String roomCode) {
    return new SocketIOGameRoom(user, gameServerURL, roomCode);
  }
}
