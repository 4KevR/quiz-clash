package com.github.quizclash.application;

import com.github.quizclash.domain.*;

import java.util.List;

public class RemoveUserScreenProvider implements ScreenProvider, IntegerActionable {
    private final Repository repository;
    private boolean hasNextScreen = true;
    private ScreenProviderType nextScreenProviderType;

    public RemoveUserScreenProvider (Repository repository){
        this.repository = repository;
    }

    public Screen fetchScreen() {
        String menuTitle = "Which user do you want to remove?";
        List<User> users = this.repository.getUserRepository().getUsers();
        return new OptionScreen(menuTitle, users);
    }

    public void submitAction(Action<Integer> action) {
        int actionValue = action.getActionValue();
        List<User> users = this.repository.getUserRepository().getUsers();
        if (actionValue > 0 && actionValue <= users.size()) {
            this.repository.getUserRepository().removeUser(users.get(actionValue - 1));
        }
        this.nextScreenProviderType = ScreenProviderType.USER_MENU;
        this.hasNextScreen = false;
    }

    public ScreenProviderType getNextScreenProviderType() {
        return this.nextScreenProviderType;
    }

    public boolean hasNextScreen() {
        return this.hasNextScreen;
    }
}
