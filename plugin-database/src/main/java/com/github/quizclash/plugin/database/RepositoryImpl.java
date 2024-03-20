package com.github.quizclash.plugin.database;

import com.github.quizclash.domain.CategoryRepository;
import com.github.quizclash.domain.Repository;
import com.github.quizclash.domain.UserRepository;

public class RepositoryImpl implements Repository {
  private final CategoryRepository categoryRepository;
  private final UserRepository userRepository;

  public RepositoryImpl(CategoryRepository categoryRepository, UserRepository userRepository) {
    this.categoryRepository = categoryRepository;
    this.userRepository = userRepository;
  }

  public CategoryRepository getCategoryRepository() {
    return categoryRepository;
  }

  public UserRepository getUserRepository() {
    return userRepository;
  }
}
