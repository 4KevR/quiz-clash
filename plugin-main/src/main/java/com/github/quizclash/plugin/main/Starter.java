package com.github.quizclash.plugin.main;

import com.github.quizclash.application.ScreenProviderManager;
import com.github.quizclash.application.TerminationException;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import com.github.quizclash.domain.Repository;
import com.github.quizclash.domain.SettingsRepository;
import com.github.quizclash.plugin.cli.QuizClashCLI;
import com.github.quizclash.plugin.database.LocalCategoryRepository;
import com.github.quizclash.plugin.database.RepositoryImpl;
import com.github.quizclash.plugin.database.SettingsRepositoryImpl;
import com.github.quizclash.plugin.database.UserRepositoryImpl;
import com.github.quizclash.plugin.network.SocketIOGameRoomManager;

import java.io.IOException;
import java.net.URI;

public class Starter {
  public static void main(String[] args) throws IOException, InvalidQuestionFormatException {
    URI gameServerURI = URI.create(args[0]);
    SocketIOGameRoomManager socketIOGameRoomManager = new SocketIOGameRoomManager(gameServerURI);
    LocalCategoryRepository categoryRepository = new LocalCategoryRepository();
    SettingsRepository settingsRepository = new SettingsRepositoryImpl();
    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    Repository repository = new RepositoryImpl(categoryRepository, settingsRepository,
        userRepository);
    try {
      QuizClashCLI quizClashCLI = new QuizClashCLI(100, 30);
      ScreenFactory cliScreenFactory = quizClashCLI.getCLIScreenFactory();
      ScreenProviderManager screenProviderManager = new ScreenProviderManager(repository,
          cliScreenFactory, socketIOGameRoomManager);
      screenProviderManager.run();
      quizClashCLI.destroy();
    } catch (TerminationException e) {
      System.err.println("QuizClash was terminated due to an error");
    }
  }
}
