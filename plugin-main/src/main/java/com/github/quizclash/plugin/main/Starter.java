package com.github.quizclash.plugin.main;

import com.github.quizclash.application.InvalidActionException;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import com.github.quizclash.domain.Repository;
import com.github.quizclash.plugin.cli.QuizClashCLI;
import com.github.quizclash.plugin.database.CategoryRepositoryImpl;
import com.github.quizclash.plugin.database.RepositoryImpl;
import com.github.quizclash.plugin.database.UserRepositoryImpl;
import java.io.IOException;

public class Starter {
  public static void main(String[] args) throws InterruptedException, IOException, InvalidQuestionFormatException, InvalidActionException {
    CategoryRepositoryImpl categoryRepository = new CategoryRepositoryImpl();
    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    Repository repository = new RepositoryImpl(categoryRepository, userRepository);
    QuizClashCLI quizClashCLI = new QuizClashCLI(100, 30, repository);
    quizClashCLI.run();
    quizClashCLI.destroy();
  }
}
