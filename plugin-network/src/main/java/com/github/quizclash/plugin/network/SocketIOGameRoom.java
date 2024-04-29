package com.github.quizclash.plugin.network;

import com.github.quizclash.application.TerminationException;
import com.github.quizclash.application.room.*;
import com.github.quizclash.domain.CategoryRepository;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import com.github.quizclash.domain.User;
import io.socket.client.IO;
import io.socket.client.Socket;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class SocketIOGameRoom extends GameRoom
    implements GameRoomLifetimeDispatcher, GameRoomActionDispatcher, GameRoomActionSender {
  private final Object lock = new Object();
  private final Socket socket;
  private final List<GameRoomListener> gameRoomListeners = new ArrayList<>();
  private String roomName;
  private List<User> players = new ArrayList<>();
  private OnlineCategoryRepository onlineCategoryRepository;

  public SocketIOGameRoom(User user, URI gameServerURL, String code) {
    super(user, code);
    this.socket = IO.socket(gameServerURL);
    socket.on("joined room", args -> {
      JSONObject response = (JSONObject) args[0];
      roomName = response.getString("room_name");
      JSONArray categories_json = response.getJSONArray("categories");
      try {
        onlineCategoryRepository = new OnlineCategoryRepository(categories_json);
      } catch (InvalidQuestionFormatException e) {
        throw new TerminationException("QuizClash was interrupted");
      }
      dispatchRoomJoin();
    });
    socket.on("show players", args -> {
      JSONObject response = (JSONObject) args[0];
      JSONArray playersArray = response.getJSONArray("players");
      players = playersArray.toList().stream().map(name -> new User((String) name)).toList();
      dispatchPlayerUpdate();
    });
    socket.on("game start", args -> dispatchGameStart());
    socket.on("game turn action", args -> dispatchGameTurnAction());
    socket.on("game turn listen", args -> dispatchGameTurnListen());
    socket.on("game turn listen category submission",
        args -> dispatchGameTurnListenCategorySubmission((Integer) args[0]));
    socket.on("game turn listen question option submission",
        args -> dispatchGameTurnListenQuestionOptionSubmission((Integer) args[0]));
    socket.on("game finished", args -> releaseGameLock());
    socket.on("room terminated", args -> releaseGameLock());
    socket.connect();
    JSONObject roomToJoin = new JSONObject();
    roomToJoin.put("user_name", this.getNameOfPlayingUser());
    roomToJoin.put("room_code", code);
    socket.emit("join", roomToJoin);
  }

  private void releaseGameLock() {
    synchronized (lock) {
      lock.notifyAll();
    }
    socket.off();
    socket.close();
  }

  @Override
  public void waitForGameToFinish() throws TerminationException {
    synchronized (lock) {
      try {
        lock.wait();
      } catch (InterruptedException e) {
        throw new TerminationException("QuizClash was interrupted");
      }
    }
  }

  @Override
  public void addListener(GameRoomListener gameRoomListener) {
    gameRoomListeners.add(gameRoomListener);
  }

  @Override
  public void dispatchRoomJoin() {
    gameRoomListeners.forEach(GameRoomListener::onJoinedRoom);
  }

  @Override
  public void dispatchPlayerUpdate() {
    gameRoomListeners.forEach(GameRoomListener::onUpdatePlayers);
  }

  @Override
  public void dispatchGameStart() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new TerminationException("QuizClash was interrupted");
    }
    gameRoomListeners.forEach(GameRoomListener::onGameStart);
  }

  @Override
  public String getRoomName() {
    return roomName;
  }

  @Override
  public List<User> getPlayers() {
    return players;
  }

  @Override
  public CategoryRepository getRoomCategoryRepository() {
    return onlineCategoryRepository;
  }

  @Override
  public void sendSelectedCategoryId(int categoryId) {
    socket.emit("game turn action category submission", categoryId);
  }

  @Override
  public void sendSelectedQuestionOptionIndex(int questionOptionIndex) {
    socket.emit("game turn action question option submission", questionOptionIndex);
  }

  @Override
  public void dispatchGameTurnAction() {
    gameRoomListeners.forEach(GameRoomListener::onGameTurnAction);
  }

  @Override
  public void dispatchGameTurnListen() {
    gameRoomListeners.forEach(GameRoomListener::onGameTurnListen);
  }

  @Override
  public void dispatchGameTurnListenCategorySubmission(int selectedCategoryId) {
    gameRoomListeners.forEach(
        gameRoomListener -> gameRoomListener.onGameTurnListenCategorySubmission(
            selectedCategoryId));
  }

  @Override
  public void dispatchGameTurnListenQuestionOptionSubmission(int selectedQuestionOptionIndex) {
    gameRoomListeners.forEach(
        gameRoomListener -> gameRoomListener.onGameTurnListenQuestionOptionSubmission(
            selectedQuestionOptionIndex));
  }
}
