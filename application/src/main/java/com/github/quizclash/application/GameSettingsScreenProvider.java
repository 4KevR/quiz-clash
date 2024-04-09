package com.github.quizclash.application;

import java.util.List;

import com.github.quizclash.domain.*;

public class GameSettingsScreenProvider implements ScreenProvider, IntegerActionable {
    private final Repository repository;
    private boolean hasNextScreen = true;
    private ScreenProviderType nextScreenProviderType;

    public GameSettingsScreenProvider(Repository repository) {
        this.repository = repository;
    }

    public Screen fetchScreen() {
        String menuTitle = "Which Settings do you want to change?";
        return new OptionScreen(menuTitle, List.of(GameSettingsEnum.values()));
    }

    public void submitAction(Action<Integer> action) {
        int actionValue = action.getActionValue();
        if(actionValue > 0 && actionValue < 3) {
            switch (actionValue) {
                case 1:
                    this.nextScreenProviderType = ScreenProviderType.CHANGE_CATEGORY_SETTINGS;
                    break;
                default:
                    this.nextScreenProviderType = ScreenProviderType.MENU;
                    break;
                }
            this.hasNextScreen = false;
        }
    }

    public ScreenProviderType getNextScreenProviderType() {
        return this.nextScreenProviderType;
    }

    public boolean hasNextScreen() {
        return this.hasNextScreen;
    }
}
