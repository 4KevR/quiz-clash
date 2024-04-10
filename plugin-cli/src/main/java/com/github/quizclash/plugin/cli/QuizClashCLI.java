package com.github.quizclash.plugin.cli;

import com.github.quizclash.plugin.cli.screen.CLIScreenFactory;

import java.io.IOException;
import java.util.List;

public class QuizClashCLI {
  private final CLIWindowManager cliWindow;

  public QuizClashCLI(int sizeX, int sizeY) throws InterruptedException, IOException {
    this.cliWindow = new CLIWindowManager(sizeX, sizeY);
    cliWindow.printAnimated("Welcome to ...", 50);
    Thread.sleep(500);
    cliWindow.clearAllCanvas();
    List<String> nameLines = ASCIResourceReader.from(ASCIResourceEnum.NAME);
    cliWindow.printLines(nameLines);
    Thread.sleep(2000);
  }

  public CLIScreenFactory getCLIScreenFactory() {
    return new CLIScreenFactory(cliWindow);
  }

  public void destroy() throws InterruptedException {
    cliWindow.clearAllCanvas();
    cliWindow.printAnimated("Thank you for playing QuizClash!", 50);
    Thread.sleep(2000);
    cliWindow.clearCLI();
  }
}
