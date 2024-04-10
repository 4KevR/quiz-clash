package com.github.quizclash.application.screen;

import com.github.quizclash.application.action.Action;

public abstract class TextInputScreen extends Screen {
  private final String inputRequest;
  public TextInputScreen(String screenName, String inputRequest) {
    super(screenName);
    this.inputRequest = inputRequest;
  }

  public abstract Action<String> getTextInput();

  public String getInputRequest() {
    return inputRequest;
  }
}
