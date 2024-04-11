package com.github.quizclash.application.screen;

import com.github.quizclash.application.action.Action;

public abstract class NumberInputScreen extends Screen {
  private final String inputRequest;

  public NumberInputScreen(String screenName, String inputRequest) {
    super(screenName);
    this.inputRequest = inputRequest;
  }

  public abstract Action<Integer> getNumberInput();

  public String getInputRequest() {
    return this.inputRequest;
  }
}
