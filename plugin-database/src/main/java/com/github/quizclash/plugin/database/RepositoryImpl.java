package com.github.quizclash.plugin.database;

import com.github.quizclash.domain.CategoryRepository;
import com.github.quizclash.domain.Repository;
import com.github.quizclash.domain.SettingsRepository;
import com.github.quizclash.domain.UserRepository;

public class RepositoryImpl implements Repository {
  private final CategoryRepository categoryRepository;
  private final SettingsRepository settingsRepository;
  private final UserRepository userRepository;

  public RepositoryImpl(CategoryRepository categoryRepository, SettingsRepository settingsRepository, UserRepository userRepository) {
    this.categoryRepository = categoryRepository;
    this.settingsRepository = settingsRepository;
    this.userRepository = userRepository;
  }

  public CategoryRepository getCategoryRepository() {
    return categoryRepository;
  } 

  public SettingsRepository getSettingsRepository() {
    return settingsRepository;
  }

  public UserRepository getUserRepository() {
    return userRepository;
  }
}
