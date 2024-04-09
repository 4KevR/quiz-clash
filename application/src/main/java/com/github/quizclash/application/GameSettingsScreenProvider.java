package com.github.quizclash.application;

import java.util.List;

import com.github.quizclash.domain.Actionable;
import com.github.quizclash.domain.GameSettingsEnum;
import com.github.quizclash.domain.OptionScreen;
import com.github.quizclash.domain.Repository;
import com.github.quizclash.domain.Screen;
import com.github.quizclash.domain.ScreenProvider;

public class GameSettingsScreenProvider implements ScreenProvider{
    private final Repository repository;
    private boolean hasNextScreen = true;
    private ScreenProvider nextScreenProvider;

    public GameSettingsScreenProvider(Repository repository) {
        this.repository = repository;
    }

    public Screen fetchScreen() {
        String menuTitle = "Which Settings do you want to change?";
        return new OptionScreen(menuTitle, List.of(GameSettingsEnum.values()));
    }

    public void submitAction(Actionable<?> action) {
        int actionValue = (int) action.getActionValue();
        if(actionValue > 0 && actionValue < 3) {
            switch (actionValue) {
                case 1:
                    this.nextScreenProvider = new ChangeCategorySettingScreenProvider(this.repository);
                    break;
                default:
                    this.nextScreenProvider = new MenuScreenProvider(this.repository);
                    break;
                }
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
