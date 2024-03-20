package com.github.quizclash.domain;

public class IntegerAction implements Actionable<Integer> {
  private final int intActionValue;

  public IntegerAction(int intActionValue) {
    this.intActionValue = intActionValue;
  }

  public Integer getActionValue() {
    return intActionValue;
  }
}
