package com.github.quizclash.application;

import com.github.quizclash.domain.Actionable;
import com.github.quizclash.domain.NumberInputScreen;
import com.github.quizclash.domain.Repository;
import com.github.quizclash.domain.Screen;
import com.github.quizclash.domain.ScreenProvider;

public class ChangeCategorySettingScreenProvider implements ScreenProvider{
    private final Repository repository;
    private boolean hasNextScreen = true;
    private ScreenProvider nextScreenProvider;

    public ChangeCategorySettingScreenProvider(Repository repository) {
        this.repository = repository;
    }

    public Screen fetchScreen() {
        return new NumberInputScreen("How many categories do you want to play per user in one game?", "Enter a number");
    }

    public void submitAction(Actionable<?> action) {
        int actionValue = (int) action.getActionValue();
        if(actionValue > 0) {
            this.repository.getSettingsRepository().setCategoriesPerGameAndUser(actionValue);
            this.nextScreenProvider = new GameSettingsScreenProvider(this.repository);
            this.hasNextScreen = false;
        }
    }

    public ScreenProvider getNextScreenProvider() {
        return this.nextScreenProvider;
    }

    public boolean hasNextScreen() {
        return this.hasNextScreen;
    }
}
