package com.github.quizclash.application.screen.provider;

public interface ScreenProvider {
  void execute();

  ScreenProviderType getNextScreenProviderType();
}
