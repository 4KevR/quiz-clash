package com.github.quizclash.domain;

public interface ScreenProvider {
  Screen fetchScreen();
  void submitAction(Actionable<?> action);
  ScreenProvider getNextScreenProvider();
  boolean hasNextScreen();
}
