package com.github.quizclash.application;

import com.github.quizclash.domain.*;

import java.util.List;

public class UserMenuScreenProvider implements ScreenProvider {

    private final Repository repository;
    private boolean hasNextScreen = true;
    private ScreenProvider nextScreenProvider;

    public UserMenuScreenProvider(Repository repository) {
        this.repository = repository;
    }

    public Screen fetchScreen() {
        String menuTitle = "What do you want to do?";
        return new OptionScreen(menuTitle, List.of(UserMenuEnum.values()));
    }

    public void submitAction(Actionable<?> action) {
        int actionValue = (int) action.getActionValue();
        if(actionValue > 0 && actionValue < 4) {
            switch(actionValue){
            case 1:
                this.nextScreenProvider = new AddUserScreenProvider(repository);
                break;
            case 2:
                this.nextScreenProvider = new RemoveUserScreenProvider(repository);
                break;
            default:
                this.nextScreenProvider = new MenuScreenProvider(repository);
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
