package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.Screen;
import com.github.quizclash.application.action.Action;
import com.github.quizclash.application.action.IntegerActionable;
import com.github.quizclash.application.screen.NumberInputScreen;
import com.github.quizclash.domain.*;

public class ChangeCategorySettingScreenProvider implements ScreenProvider, IntegerActionable {
    private final Repository repository;
    private boolean hasNextScreen = true;
    private ScreenProviderType nextScreenProviderType;

    public ChangeCategorySettingScreenProvider(Repository repository) {
        this.repository = repository;
    }

    public Screen fetchScreen() {
        return new NumberInputScreen("How many categories do you want to play per user in one game?", "Enter a number");
    }

    public void submitAction(Action<Integer> action) {
        int actionValue = action.getActionValue();
        if(actionValue > 0) {
            this.repository.getSettingsRepository().setCategoriesPerGameAndUser(actionValue);
            this.nextScreenProviderType = ScreenProviderType.GAME_SETTINGS;
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
