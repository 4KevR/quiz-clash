package com.github.quizclash.plugin.main;

import com.github.quizclash.domain.User;
import com.github.quizclash.domain.MainMenuEnum;
import com.github.quizclash.plugin.cli.QuizClashCLI;
import java.io.IOException;

public class Starter {
  public static void main(String[] args) throws InterruptedException, IOException {
    QuizClashCLI quizClashCLI = new QuizClashCLI(100, 30);
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
