package com.github.quizclash.application;

import com.github.quizclash.domain.*;

public class AddUserScreenProvider implements ScreenProvider {

    private final Repository repository;
    private boolean hasNextScreen = true;
    private ScreenProvider nextScreenProvider;

    public AddUserScreenProvider(Repository repository){
        this.repository = repository;
    }

    public Screen fetchScreen() {
        return new TextInputScreen("Add another player", "Enter name");
    }

    public void submitAction(Actionable<?> action) {
        String actionValue = (String) action.getActionValue();
        this.repository.getUserRepository().addUser(new User(actionValue));
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
