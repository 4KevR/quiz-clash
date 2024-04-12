package com.github.quizclash.plugin.network;

import com.github.quizclash.application.room.GameRoom;
import com.github.quizclash.application.room.RoomJoinException;
import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import org.json.JSONObject;

import java.net.URI;

public class SocketIOGameRoom extends GameRoom {
  private final Socket socket;
  private volatile String roomName;

  public SocketIOGameRoom(URI gameServerURL, String code) throws RoomJoinException {
    super(code);
    this.socket = IO.socket(gameServerURL);
    socket.on("show players", args -> {});
    socket.on("receive room name", args -> {
      JSONObject response = (JSONObject) args[0];
      roomName = response.getString("room_name");
    });
    socket.connect();
    JSONObject roomToJoin = new JSONObject();
    roomToJoin.put("user_name", "Test");
    roomToJoin.put("room_code", code);
    try {
      socket.emit("join", roomToJoin, (Ack) args -> {
        JSONObject response = (JSONObject) args[0];
        if (response.getString("message").equals("Ok")) {
          socket.emit("get room name");
        } else throw new RuntimeException();
      });
    } catch (RuntimeException e) {
      throw new RoomJoinException("Could not join room");
    }
  }

  @Override
  public String getRoomName() {
    while (roomName == null) {
      Thread.onSpinWait();
    }
    return roomName;
  }

  @Override
  public void registerForPlayerUpdates() {

  }

  @Override
  public void setStatusToReady() {
    socket.emit("ready");
  }

  @Override
  public void registerForGameStart() {

  }
}
