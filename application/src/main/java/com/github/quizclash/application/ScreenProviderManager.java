package com.github.quizclash.application;

import com.github.quizclash.application.room.GameRoomManager;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.application.screen.provider.*;
import com.github.quizclash.domain.Repository;

public class ScreenProviderManager {
  private final Repository repository;
  private final ScreenFactory screenFactory;
  private final GameRoomManager gameRoomManager;
  private ScreenProvider currentScreenProvider;

  public ScreenProviderManager(Repository repository,
                               ScreenFactory screenFactory,
                               GameRoomManager gameRoomManager) {
    this.repository = repository;
    this.screenFactory = screenFactory;
    this.gameRoomManager = gameRoomManager;
    this.currentScreenProvider = new WelcomeScreenProvider(repository, screenFactory);
  }

  public void run() throws InterruptedException {
    while (currentScreenProvider != null) {
      currentScreenProvider.execute();
      this.updateScreenProvider();
    }
  }

  public void updateScreenProvider() {
    ScreenProviderType nextScreenProviderType = currentScreenProvider.getNextScreenProviderType();
    if (nextScreenProviderType == null) {
      currentScreenProvider = null;
      return;
    }
    switch (nextScreenProviderType) {
      case ADD_USER -> currentScreenProvider = new AddUserScreenProvider(repository, screenFactory);
      case CHANGE_CATEGORY_SETTINGS ->
          currentScreenProvider = new ChangeCategorySettingScreenProvider(repository,
              screenFactory);
      case GAME_MODE ->
          currentScreenProvider = new GameModeScreenProvider(repository, screenFactory);
      case GAME_SETTINGS ->
          currentScreenProvider = new GameSettingsScreenProvider(repository, screenFactory);
      case LOCAL_MULTIPLAYER ->
          currentScreenProvider = new LocalMultiplayerScreenProvider(repository, screenFactory);
      case MENU -> currentScreenProvider = new MenuScreenProvider(repository, screenFactory);
      case ONLINE_MULTIPLAYER ->
          currentScreenProvider = new OnlineMultiplayerScreenProvider(repository, screenFactory,
              gameRoomManager);
      case REMOVE_USER ->
          currentScreenProvider = new RemoveUserScreenProvider(repository, screenFactory);
      case TRAINING ->
          currentScreenProvider = new TrainingScreenProvider(repository, screenFactory);
      case USER_MENU ->
          currentScreenProvider = new UserMenuScreenProvider(repository, screenFactory);
      case WELCOME -> currentScreenProvider = new WelcomeScreenProvider(repository, screenFactory);
    }
  }
}
