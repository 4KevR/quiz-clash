package com.github.quizclash.plugin.main;

import com.github.quizclash.application.ScreenProviderManager;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import com.github.quizclash.domain.Repository;
import com.github.quizclash.domain.SettingsRepository;
import com.github.quizclash.plugin.cli.QuizClashCLI;
import com.github.quizclash.plugin.database.CategoryRepositoryImpl;
import com.github.quizclash.plugin.database.RepositoryImpl;
import com.github.quizclash.plugin.database.SettingsRepositoryImpl;
import com.github.quizclash.plugin.database.UserRepositoryImpl;

import java.io.IOException;

public class Starter {
  public static void main(String[] args)
      throws InterruptedException, IOException, InvalidQuestionFormatException {
    CategoryRepositoryImpl categoryRepository = new CategoryRepositoryImpl();
    SettingsRepository settingsRepository = new SettingsRepositoryImpl();
    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    Repository repository = new RepositoryImpl(categoryRepository, settingsRepository,
        userRepository);
    QuizClashCLI quizClashCLI = new QuizClashCLI(100, 30);
    ScreenFactory cliScreenFactory = quizClashCLI.getCLIScreenFactory();
    ScreenProviderManager screenProviderManager = new ScreenProviderManager(repository,
        cliScreenFactory);
    screenProviderManager.run();
    quizClashCLI.destroy();
  }
}
