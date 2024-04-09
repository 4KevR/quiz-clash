package com.github.quizclash.application;

import com.github.quizclash.application.action.Action;
import com.github.quizclash.application.action.IntegerActionable;
import com.github.quizclash.application.action.InvalidActionException;
import com.github.quizclash.application.action.StringActionable;
import com.github.quizclash.application.screen.provider.*;
import com.github.quizclash.domain.*;

public class ScreenProviderManager {
    private final Repository repository;
    private ScreenProvider currentScreenProvider;

    public ScreenProviderManager(Repository repository) {
        this.repository = repository;
        this.currentScreenProvider = new WelcomeScreenProvider(repository);
    }

    public ScreenProvider getCurrentScreenProvider() {
        return currentScreenProvider;
    }

    public void updateScreenProvider() {
        ScreenProviderType nextScreenProviderType = currentScreenProvider.getNextScreenProviderType();
        if (nextScreenProviderType == null) {
            currentScreenProvider = null;
            return;
        }
        switch (nextScreenProviderType) {
            case ADD_USER -> currentScreenProvider = new AddUserScreenProvider(repository);
            case CHANGE_CATEGORY_SETTINGS -> currentScreenProvider = new ChangeCategorySettingScreenProvider(repository);
            case GAME_MODE -> currentScreenProvider = new GameModeScreenProvider(repository);
            case GAME_SETTINGS -> currentScreenProvider = new GameSettingsScreenProvider(repository);
            case LOCAL_MULTIPLAYER -> currentScreenProvider = new LocalMultiplayerScreenProvider(repository);
            case MENU -> currentScreenProvider = new MenuScreenProvider(repository);
            case REMOVE_USER -> currentScreenProvider = new RemoveUserScreenProvider(repository);
            case TRAINING -> currentScreenProvider = new TrainingScreenProvider(repository);
            case USER_MENU -> currentScreenProvider = new UserMenuScreenProvider(repository);
            case WELCOME -> currentScreenProvider = new WelcomeScreenProvider(repository);
        }
    }

    public void submitStringAction(Action<String> action) throws InvalidActionException {
        if (currentScreenProvider instanceof StringActionable stringActionableScreen) {
            stringActionableScreen.submitAction(action);
        } else {
            throw new InvalidActionException("The current screen does not support string actions");
        }
    }

    public void submitIntegerAction(Action<Integer> action) throws InvalidActionException {
        if (currentScreenProvider instanceof IntegerActionable integerActionableScreen) {
            integerActionableScreen.submitAction(action);
        } else {
            throw new InvalidActionException("The current screen does not support integer actions");
        }
    }
}
