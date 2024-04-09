package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.OptionScreen;
import com.github.quizclash.application.screen.Screen;
import com.github.quizclash.application.screen.displayables.UserMenuEnum;
import com.github.quizclash.application.action.Action;
import com.github.quizclash.application.action.IntegerActionable;
import com.github.quizclash.domain.*;

import java.util.List;

public class UserMenuScreenProvider implements ScreenProvider, IntegerActionable {
    private final Repository repository;
    private boolean hasNextScreen = true;
    private ScreenProviderType nextScreenProviderType;

    public UserMenuScreenProvider(Repository repository) {
        this.repository = repository;
    }

    public Screen fetchScreen() {
        String menuTitle = "What do you want to do?";
        return new OptionScreen(menuTitle, List.of(UserMenuEnum.values()));
    }

    public void submitAction(Action<Integer> action) {
        int actionValue = action.getActionValue();
        if(actionValue > 0 && actionValue < 4) {
            switch(actionValue){
            case 1:
                this.nextScreenProviderType = ScreenProviderType.ADD_USER;
                break;
            case 2:
                this.nextScreenProviderType = ScreenProviderType.REMOVE_USER;
                break;
            default:
                this.nextScreenProviderType = ScreenProviderType.MENU;
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
