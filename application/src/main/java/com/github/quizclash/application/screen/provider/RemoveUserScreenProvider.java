package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.OptionScreen;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.domain.*;

import java.util.List;

public class RemoveUserScreenProvider implements ScreenProvider {
    private final Repository repository;
    private final ScreenFactory screenFactory;
    private ScreenProviderType nextScreenProviderType;

    public RemoveUserScreenProvider (Repository repository, ScreenFactory screenFactory){
        this.repository = repository;
        this.screenFactory = screenFactory;
    }

    @Override
    public void execute() throws InterruptedException {
        int userToRemove = 0;
        String menuTitle = "Which user do you want to remove?";
        List<User> users = this.repository.getUserRepository().getUsers();
        while (userToRemove <= 0 || userToRemove > users.size()) {
            OptionScreen optionScreen = screenFactory.createOptionScreen(menuTitle, users);
            optionScreen.render();
            userToRemove = optionScreen.getOptionInput().getActionValue();
        }
        this.repository.getUserRepository().removeUser(users.get(userToRemove - 1));
        this.nextScreenProviderType = ScreenProviderType.USER_MENU;
    }

    public ScreenProviderType getNextScreenProviderType() {
        return this.nextScreenProviderType;
    }
}
