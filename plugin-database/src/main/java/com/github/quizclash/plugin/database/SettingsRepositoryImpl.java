package com.github.quizclash.plugin.database;

import com.github.quizclash.domain.SettingsRepository;

public class SettingsRepositoryImpl implements SettingsRepository {
    private int categoriesPerGameAndUser = 4;

    public int getCategoriesPerGameAndUser() {
        return this.categoriesPerGameAndUser;
    }

    public void setCategoriesPerGameAndUser(int numberOfCategories) {
        this.categoriesPerGameAndUser = numberOfCategories;
    }

}
