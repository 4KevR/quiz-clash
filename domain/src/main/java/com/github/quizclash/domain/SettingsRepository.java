package com.github.quizclash.domain;

public interface SettingsRepository {
    int getCategoriesPerGameAndUser();
    void setCategoriesPerGameAndUser(int numberOfCategories);
}
