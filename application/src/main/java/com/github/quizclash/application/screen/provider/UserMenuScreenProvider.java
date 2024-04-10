package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.OptionScreen;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.application.screen.displayables.UserMenuEnum;
import com.github.quizclash.domain.*;

import java.util.List;

public class UserMenuScreenProvider implements ScreenProvider {
    private final Repository repository;
    private final ScreenFactory screenFactory;
    private ScreenProviderType nextScreenProviderType;

    public UserMenuScreenProvider(Repository repository, ScreenFactory screenFactory) {
        this.repository = repository;
        this.screenFactory = screenFactory;
    }

    @Override
    public void execute() throws InterruptedException {
        int selection = 0;
        String menuTitle = "What do you want to do?";
        while (selection <= 0 || selection > 3) {
            OptionScreen optionScreen = screenFactory.createOptionScreen(menuTitle, List.of(UserMenuEnum.values()));
            optionScreen.render();
            selection = optionScreen.getOptionInput().getActionValue();
            switch (selection) {
                case 1:
                    this.nextScreenProviderType = ScreenProviderType.ADD_USER;
                    break;
                case 2:
                    this.nextScreenProviderType = ScreenProviderType.REMOVE_USER;
                    break;
                default:
                    this.nextScreenProviderType = ScreenProviderType.MENU;
            }
        }
    }

    public ScreenProviderType getNextScreenProviderType() {
        return this.nextScreenProviderType;
    }
}
