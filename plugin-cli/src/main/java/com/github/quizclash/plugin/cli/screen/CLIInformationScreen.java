package com.github.quizclash.plugin.cli.screen;

import com.github.quizclash.application.screen.InformationScreen;
import com.github.quizclash.plugin.cli.CLIWindowManager;

import java.util.List;

public class CLIInformationScreen extends InformationScreen {
  CLIWindowManager cliWindow;

  public CLIInformationScreen(String screenName, List<String> lines, CLIWindowManager cliWindow) {
    super(screenName, lines);
    this.cliWindow = cliWindow;
  }

  @Override
  public void render() throws InterruptedException {
    cliWindow.clearAllCanvas();
    cliWindow.printAnimated(this.getScreenName(), 20);
    cliWindow.moveOnCanvas(0, 2);
    for (String line : this.getLines()) {
      cliWindow.println(line);
    }
    cliWindow.moveToActionField();
    cliWindow.waitForEnter();
  }
}
