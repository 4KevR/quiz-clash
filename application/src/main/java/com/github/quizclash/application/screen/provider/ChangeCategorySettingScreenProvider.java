package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.NumberInputScreen;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.domain.Repository;

public class ChangeCategorySettingScreenProvider implements ScreenProvider {
  private final Repository repository;
  private final ScreenFactory screenFactory;
  private ScreenProviderType nextScreenProviderType;

  public ChangeCategorySettingScreenProvider(Repository repository, ScreenFactory screenFactory) {
    this.repository = repository;
    this.screenFactory = screenFactory;
  }

  @Override
  public void execute() throws InterruptedException {
    int categoriesPerUserInGame = 0;
    while (categoriesPerUserInGame == 0) {
      NumberInputScreen numberInputScreen = screenFactory.createNumberInputScreen(
          "How many categories do you want to play per user in one game?", "Enter a number");
      numberInputScreen.render();
      categoriesPerUserInGame = numberInputScreen.getNumberInput().getActionValue();
      if (categoriesPerUserInGame > 0) {
        this.repository.getSettingsRepository()
            .setCategoriesPerGameAndUser(categoriesPerUserInGame);
        this.nextScreenProviderType = ScreenProviderType.GAME_SETTINGS;
      }
    }
  }

  public ScreenProviderType getNextScreenProviderType() {
    return this.nextScreenProviderType;
  }
}
