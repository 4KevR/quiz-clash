package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.Screen;
import com.github.quizclash.application.action.Action;
import com.github.quizclash.application.action.StringActionable;
import com.github.quizclash.application.screen.TextInputScreen;
import com.github.quizclash.domain.*;

public class AddUserScreenProvider implements ScreenProvider, StringActionable {

    private final Repository repository;
    private boolean hasNextScreen = true;
    private ScreenProviderType nextScreenProviderType;

    public AddUserScreenProvider(Repository repository){
        this.repository = repository;
    }

    public Screen fetchScreen() {
        return new TextInputScreen("Add another player", "Enter name");
    }

    public void submitAction(Action<String> action) {
        String actionValue =  action.getActionValue();
        this.repository.getUserRepository().addUser(new User(actionValue));
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
