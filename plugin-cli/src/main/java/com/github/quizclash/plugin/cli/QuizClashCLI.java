package com.github.quizclash.plugin.cli;

import java.io.IOException;
import java.util.List;

public class QuizClashCLI {
  private final CLIWindowManager cliWindow;

  public QuizClashCLI(int sizeX, int sizeY) {
    cliWindow = new CLIWindowManager(sizeX, sizeY);
  }

  public void startGame() throws InterruptedException, IOException {
    cliWindow.printAnimated("Welcome to ...", 100);
    cliWindow.clearCanvas();
    List<String> nameLines = ASCIResourceReader.from(ResourceEnum.NAME);
    cliWindow.printLines(nameLines);
    cliWindow.moveToActionField();
    cliWindow.getTextInput("Enter your name");
    cliWindow.clearCanvas();
  }

  public void destroy() throws InterruptedException {
    cliWindow.clearCanvas();
    cliWindow.printAnimated("Thank you for playing QuizClash!", 100);
    Thread.sleep(2000);
    cliWindow.clearCLI();
  }
}
