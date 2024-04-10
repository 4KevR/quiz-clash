package com.github.quizclash.plugin.cli.screen;

import com.github.quizclash.application.screen.*;
import com.github.quizclash.domain.Displayable;
import com.github.quizclash.plugin.cli.CLIWindowManager;

import java.util.List;

public class CLIScreenFactory implements ScreenFactory {
  CLIWindowManager cliWindow;

  public CLIScreenFactory(CLIWindowManager cliWindow) {
    this.cliWindow = cliWindow;
  }

  @Override
  public InformationScreen createInformationScreen(String screenName, List<String> lines) {
    return new CLIInformationScreen(screenName, lines, cliWindow);
  }

  @Override
  public NumberInputScreen createNumberInputScreen(String screenName, String inputRequest) {
    return new CLINumberInputScreen(screenName, inputRequest, cliWindow);
  }

  @Override
  public OptionScreen createOptionScreen(String screenName, List<? extends Displayable> displayableList) {
    return new CLIOptionScreen(screenName, displayableList, cliWindow);
  }

  @Override
  public TextInputScreen createTextInputScreen(String screenName, String inputRequest) {
    return new CLITextInputScreen(screenName, inputRequest, cliWindow);
  }
}
