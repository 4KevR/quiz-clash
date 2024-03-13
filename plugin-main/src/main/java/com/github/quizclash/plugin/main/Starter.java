package com.github.quizclash.plugin.main;

import com.github.quizclash.plugin.cli.QuizClashCLI;
import java.io.IOException;

public class Starter {
  public static void main(String[] args) throws InterruptedException, IOException {
    QuizClashCLI quizClashCLI = new QuizClashCLI(100, 30);
    quizClashCLI.startGame();
    quizClashCLI.destroy();
  }
}
