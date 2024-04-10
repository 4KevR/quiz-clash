package com.github.quizclash.application.screen.provider;

public interface ScreenProvider {
  void execute() throws InterruptedException;
  ScreenProviderType getNextScreenProviderType();
}
