package com.github.quizclash.domain;

public interface ScreenProvider {
  Screen fetchScreen();
  ScreenProviderType getNextScreenProviderType();
  boolean hasNextScreen();
}
