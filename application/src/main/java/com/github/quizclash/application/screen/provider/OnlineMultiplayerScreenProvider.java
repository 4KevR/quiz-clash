package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.QuizGame;
import com.github.quizclash.application.TerminationException;
import com.github.quizclash.application.room.*;
import com.github.quizclash.application.screen.*;
import com.github.quizclash.application.screen.menu.MenuCreator;
import com.github.quizclash.application.screen.menu.OnlineMultiplayerMenuEnum;
import com.github.quizclash.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class OnlineMultiplayerScreenProvider implements ScreenProvider, GameRoomListener {
  private final Repository repository;
  private final ScreenFactory screenFactory;
  private final GameRoomManager gameRoomManager;
  private final User playingUser;
  private GameRoom gameRoom;
  private QuizGame onlineQuizGame;

  public OnlineMultiplayerScreenProvider(Repository repository,
                                         ScreenFactory screenFactory,
                                         GameRoomManager gameRoomManager) {
    this.repository = repository;
    this.screenFactory = screenFactory;
    this.gameRoomManager = gameRoomManager;
    this.playingUser = repository.getUserRepository().getUsers().get(0);
  }

  @Override
  public void execute() {
    String menuTitle = "What do you want to do?";
    MenuCreator menuCreator = new MenuCreator(menuTitle, OnlineMultiplayerMenuEnum.values(),
        screenFactory);
    int selection = menuCreator.displayAndGetSelection();
    try {
      if (selection == 1) {
        this.createNewRoom();
      } else {
        this.joinRoom();
      }
      gameRoom.addListener(this);
      gameRoom.waitForGameToFinish();
      List<String> lines = new ArrayList<>();
      Player[] players = onlineQuizGame.getPlayers();
      for (Player player : players) {
        lines.add(String.format("%s - %d points", player.getPlayerName(),
            player.getCurrentScore().getIntScore()));
      }
      screenFactory.createInformationScreen("Result", lines).render();
    } catch (GameRoomCreationException e) {
      List<String> exceptionMessage = List.of("Room creation was not possible");
      screenFactory.createInformationScreen("Operation failed", exceptionMessage).render();
    } catch (GameRoomJoinException e) {
      List<String> exceptionMessage = List.of("Joining the room was not possible");
      screenFactory.createInformationScreen("Operation failed", exceptionMessage).render();
    }
  }

  @Override
  public ScreenProviderType getNextScreenProviderType() {
    return ScreenProviderType.MENU;
  }

  private void createNewRoom() throws GameRoomCreationException, GameRoomJoinException {
    TextInputScreen createRoomScreen = screenFactory.createTextInputScreen(
        "Create a new room by giving it a name", "Enter name for new room");
    createRoomScreen.render();
    String roomName = createRoomScreen.getTextInput().getActionValue();
    NumberInputScreen amountOfPlayersScreen = screenFactory.createNumberInputScreen(
        "Define with how many players you want to play", "Amount of players");
    amountOfPlayersScreen.render();
    int amountOfPlayers = amountOfPlayersScreen.getNumberInput().getActionValue();
    gameRoom = gameRoomManager.createRoom(playingUser, roomName, amountOfPlayers);
  }

  private void joinRoom() throws GameRoomJoinException {
    TextInputScreen joinRoomScreen = screenFactory.createTextInputScreen(
        "Join a room by entering the game code of the desired room", "Enter room code");
    joinRoomScreen.render();
    String roomCode = joinRoomScreen.getTextInput().getActionValue();
    gameRoom = gameRoomManager.joinRoom(playingUser, roomCode);
  }

  private void displayCategorySelection(boolean isLocalTurn) {
    String playerName = onlineQuizGame.getCurrentPlayer().getPlayerName();
    Screen categoryScreen;
    List<? extends Displayable> remainingCategories = onlineQuizGame.getRemainingGameCategories();
    if (isLocalTurn) {
      String screenName = playerName + ", select category";
      categoryScreen = screenFactory.createOptionScreen(screenName, remainingCategories);
    } else {
      String screenName = playerName + " is selecting category";
      List<String> categoryLines = IntStream
          .range(0, remainingCategories.size())
          .mapToObj(i -> (i + 1) + ") " + remainingCategories.get(i).getDisplayName())
          .toList();
      categoryScreen = screenFactory.createInformationScreen(screenName, categoryLines, false);
    }
    categoryScreen.render();
    if (isLocalTurn) {
      int action = ((OptionScreen) categoryScreen).getOptionInput().getActionValue();
      onlineQuizGame.setCurrentCategory(action - 1);
      gameRoom.sendSelectedCategoryId(onlineQuizGame.getCurrentCategory().getId());
    }
  }

  @Override
  public void onJoinedRoom() {
    String roomName = gameRoom.getRoomName();
    String roomCode = gameRoom.getCode();
    List<String> displayText = List.of("Your room with the name " + roomName + " is ready",
        "Invite other players by giving them the code " + roomCode);
    screenFactory.createInformationScreen("Room ready!", displayText, false).render();
  }

  @Override
  public void onUpdatePlayers() {
    String roomName = gameRoom.getRoomName();
    String roomCode = gameRoom.getCode();
    List<User> users = gameRoom.getPlayers();
    List<String> userText = users.stream().map(User::getName).toList();
    screenFactory
        .createInformationScreen("Room: " + roomName + " (" + roomCode + ")", userText, false)
        .render();
  }

  @Override
  public void onGameStart() {
    CategoryRepository categoryRepository = gameRoom.getRoomCategoryRepository();
    List<User> usersToPlay = gameRoom.getPlayers();
    Player[] players = usersToPlay
        .stream()
        .map(user -> new Player(user.getName()))
        .toArray(Player[]::new);
    onlineQuizGame = new QuizGame(categoryRepository, 4 * players.length, players);
  }

  @Override
  public void onGameTurnAction() {
    this.displayCategorySelection(true);
    Question currentQuestion = onlineQuizGame.getCurrentQuestion();
    OptionScreen optionScreen = screenFactory.createOptionScreen(
        "Question: " + currentQuestion.getQuestion(),
        List.of(currentQuestion.getQuestionOptions()));
    optionScreen.render();
    int action = optionScreen.getOptionInput().getActionValue();
    onlineQuizGame.submitQuestionAnswer(action - 1);
    gameRoom.sendSelectedQuestionOptionIndex(action - 1);
  }

  @Override
  public void onGameTurnListen() {
    this.displayCategorySelection(false);
  }

  @Override
  public void onGameTurnListenCategorySubmission(int selectedCategoryId) {
    onlineQuizGame.setCurrentCategoryById(selectedCategoryId);
    String playerName = onlineQuizGame.getCurrentPlayer().getPlayerName();
    String categoryName = onlineQuizGame.getCurrentCategory().getCategoryName();
    screenFactory
        .createInformationScreen("Category selection",
            List.of("Player " + playerName + " selected category " + categoryName), false)
        .render();
    try {
      Thread.sleep(2500);
    } catch (InterruptedException e) {
      throw new TerminationException("QuizClash was interrupted");
    }
    Question currentQuestion = onlineQuizGame.getCurrentQuestion();
    QuestionOption[] currentQuestionOptions = currentQuestion.getQuestionOptions();
    List<String> questionOptionLines = IntStream
        .range(0, currentQuestionOptions.length)
        .mapToObj(i -> (i + 1) + ") " + currentQuestionOptions[i].getDisplayName())
        .toList();
    screenFactory
        .createInformationScreen("Question: " + currentQuestion.getQuestion(), questionOptionLines,
            false)
        .render();
  }

  @Override
  public void onGameTurnListenQuestionOptionSubmission(int selectedQuestionOptionIndex) {
    onlineQuizGame.submitQuestionAnswer(selectedQuestionOptionIndex);
  }
}
