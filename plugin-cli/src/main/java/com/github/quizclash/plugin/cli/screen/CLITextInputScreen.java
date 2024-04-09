package com.github.quizclash.plugin.cli.screen;

import com.github.quizclash.application.action.Action;
import com.github.quizclash.application.screen.TextInputScreen;
import com.github.quizclash.plugin.cli.CLIWindowManager;

public class CLITextInputScreen extends TextInputScreen {
  private final CLIWindowManager cliWindow;
  private String userInput;

  public CLITextInputScreen(String screenName, String inputRequest, CLIWindowManager cliWindow) {
    super(screenName, inputRequest);
    this.cliWindow = cliWindow;
  }

  @Override
  public void render() throws InterruptedException {
    cliWindow.clearAllCanvas();
    cliWindow.printAnimated(this.getScreenName(), 20);
    cliWindow.moveOnCanvas(0, 2);
    cliWindow.moveToActionField();
    userInput = cliWindow.getTextInput(this.getInputRequest());
  }

  @Override
  public Action<String> getTextInput() {
    return new Action<>(userInput);
  }
}
