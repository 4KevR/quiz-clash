package com.github.quizclash.plugin.main;

import com.github.quizclash.domain.InvalidQuestionFormatException;
import com.github.quizclash.domain.User;
import com.github.quizclash.domain.MainMenuEnum;
import com.github.quizclash.plugin.cli.QuizClashCLI;
import com.github.quizclash.plugin.database.CategoryRepository;
import java.io.IOException;
import java.util.Arrays;

public class Starter {
  public static void main(String[] args) throws InterruptedException, IOException, InvalidQuestionFormatException {
    QuizClashCLI quizClashCLI = new QuizClashCLI(100, 30);
    CategoryRepository categoryRepository = new CategoryRepository();
    User user = new User(quizClashCLI.getUserName());
    boolean terminateApp = false;
    while(!terminateApp) {
      MainMenuEnum selectedMenuEntry = quizClashCLI.selectFromMainMenu(user);
      if (selectedMenuEntry.equals(MainMenuEnum.START_GAME)) {
        quizClashCLI.selectGame();
      } else if (selectedMenuEntry.equals(MainMenuEnum.QUIT)) {
        terminateApp = true;
      }
    }
    quizClashCLI.destroy();
  }
}
