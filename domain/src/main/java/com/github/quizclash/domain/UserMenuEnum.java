package com.github.quizclash.domain;

public enum UserMenuEnum implements Displayable{
    ADD_USER("Add User"),
    REMOVE_USER("Remove User"),
    BACK("Back");

    private final String displayName;

    UserMenuEnum(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return this.displayName;
    }
}
