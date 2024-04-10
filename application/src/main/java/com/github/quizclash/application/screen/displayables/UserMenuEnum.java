package com.github.quizclash.application.screen.displayables;

import com.github.quizclash.domain.Displayable;

public enum UserMenuEnum implements Displayable {
    ADD_USER("Add User"),
    REMOVE_USER("Remove User"),
    BACK("Back to main menu");

    private final String displayName;

    UserMenuEnum(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return this.displayName;
    }
}
