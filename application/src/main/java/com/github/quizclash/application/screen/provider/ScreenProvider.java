package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.Screen;

public interface ScreenProvider {
  Screen fetchScreen();
  ScreenProviderType getNextScreenProviderType();
  boolean hasNextScreen();
}
