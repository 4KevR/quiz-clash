package com.github.quizclash.plugin.cli.screen;

import com.github.quizclash.application.action.Action;
import com.github.quizclash.application.screen.NumberInputScreen;
import com.github.quizclash.plugin.cli.CLIWindowManager;

public class CLINumberInputScreen extends NumberInputScreen {
  private final CLIWindowManager cliWindow;
  private int userInput;

  public CLINumberInputScreen(String screenName, String inputRequest, CLIWindowManager cliWindow) {
    super(screenName, inputRequest);
    this.cliWindow = cliWindow;
  }

  @Override
  public void render() {
    cliWindow.clearAllCanvas();
    cliWindow.printAnimated(this.getScreenName(), 20);
    cliWindow.moveOnCanvas(0, 2);
    cliWindow.moveToActionField();
    userInput = cliWindow.getNumberInput(this.getInputRequest());
  }

  @Override
  public Action<Integer> getNumberInput() {
    return new Action<>(userInput);
  }
}
