package com.github.quizclash.domain;

public class TextAction implements Actionable<String> {
  private final String textActionValue;

  public TextAction(String textActionValue) {
    this.textActionValue = textActionValue;
  }

  public String getActionValue() {
    return textActionValue;
  }
}
