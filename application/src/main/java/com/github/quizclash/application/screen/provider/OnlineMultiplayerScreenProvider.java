package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.room.GameRoom;
import com.github.quizclash.application.room.GameRoomManager;
import com.github.quizclash.application.room.RoomCreationException;
import com.github.quizclash.application.room.RoomJoinException;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.application.screen.TextInputScreen;
import com.github.quizclash.application.screen.menu.MenuCreator;
import com.github.quizclash.application.screen.menu.OnlineMultiplayerMenuEnum;
import com.github.quizclash.domain.Repository;

import java.util.List;

public class OnlineMultiplayerScreenProvider implements ScreenProvider {
  private final Repository repository;
  private final ScreenFactory screenFactory;
  private final GameRoomManager gameRoomManager;

  public OnlineMultiplayerScreenProvider(Repository repository,
                                         ScreenFactory screenFactory,
                                         GameRoomManager gameRoomManager) {
    this.repository = repository;
    this.screenFactory = screenFactory;
    this.gameRoomManager = gameRoomManager;
  }

  @Override
  public void execute() throws InterruptedException {
    String menuTitle = "What do you want to do?";
    MenuCreator menuCreator = new MenuCreator(menuTitle, OnlineMultiplayerMenuEnum.values(),
        screenFactory);
    int selection = menuCreator.displayAndGetSelection();
    try {
      GameRoom gameRoom = selection == 1 ? this.createNewRoom() : this.joinRoom();
      this.showPreGameScreens(gameRoom);
    } catch (RoomCreationException e) {
      List<String> exceptionMessage = List.of("Room creation was not possible");
      screenFactory.createInformationScreen("Operation failed", exceptionMessage).render();
    } catch (RoomJoinException e) {
      List<String> exceptionMessage = List.of("Joining the room was not possible");
      screenFactory.createInformationScreen("Operation failed", exceptionMessage).render();
    }
  }

  @Override
  public ScreenProviderType getNextScreenProviderType() {
    return ScreenProviderType.MENU;
  }

  private GameRoom createNewRoom()
      throws InterruptedException, RoomCreationException, RoomJoinException {
    TextInputScreen createRoomScreen = screenFactory.createTextInputScreen(
        "Create a new room by giving it a name", "Enter name for new room");
    createRoomScreen.render();
    String roomName = createRoomScreen.getTextInput().getActionValue();
    return gameRoomManager.createRoom(roomName);
  }

  private GameRoom joinRoom() throws InterruptedException, RoomJoinException {
    TextInputScreen joinRoomScreen = screenFactory.createTextInputScreen(
        "Join a room by entering the game code of the desired room", "Enter room code");
    joinRoomScreen.render();
    String roomCode = joinRoomScreen.getTextInput().getActionValue();
    return gameRoomManager.joinRoom(roomCode);
  }

  private void showPreGameScreens(GameRoom gameRoom) throws InterruptedException {
    String roomName = gameRoom.getRoomName();
    screenFactory.createInformationScreen("Game room: " + roomName,
        List.of("Game code: " + gameRoom.getCode())).render();
  }
}
