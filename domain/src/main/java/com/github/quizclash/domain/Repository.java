package com.github.quizclash.domain;

public interface Repository {
  CategoryRepository getCategoryRepository();

  SettingsRepository getSettingsRepository();

  UserRepository getUserRepository();
}
