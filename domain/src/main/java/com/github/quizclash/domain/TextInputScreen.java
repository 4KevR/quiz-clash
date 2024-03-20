package com.github.quizclash.domain;

public class TextInputScreen extends Screen {
  private final String inputRequest;
  public TextInputScreen(String screenName, String inputRequest) {
    super(screenName);
    this.inputRequest = inputRequest;
  }

  public String getInputRequest() {
    return inputRequest;
  }
}
