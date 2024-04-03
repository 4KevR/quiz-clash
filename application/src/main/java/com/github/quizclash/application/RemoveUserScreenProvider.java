package com.github.quizclash.application;

import com.github.quizclash.domain.*;

import java.util.Collections;
import java.util.List;

public class RemoveUserScreenProvider implements ScreenProvider {
    private final Repository repository;
    private boolean hasNextScreen = true;
    private ScreenProvider nextScreenProvider;

    public RemoveUserScreenProvider (Repository repository){
        this.repository = repository;
    }

    public Screen fetchScreen() {
        String menuTitle = "Which user do you want to remove?";
        List<User> users = this.repository.getUserRepository().getUsers();
        return new OptionScreen(menuTitle, users);
    }

    public void submitAction(Actionable<?> action) {
        int actionValue = (int) action.getActionValue();
        List<User> users = this.repository.getUserRepository().getUsers();
        if (actionValue > 0 && actionValue <= users.size()) {
            this.repository.getUserRepository().removeUser(users.get(actionValue - 1));
        }
        this.nextScreenProvider = new UserMenuScreenProvider(this.repository);
        this.hasNextScreen = false;
    }

    public ScreenProvider getNextScreenProvider() {
        return this.nextScreenProvider;
    }

    public boolean hasNextScreen() {
        return this.hasNextScreen;
    }
}
