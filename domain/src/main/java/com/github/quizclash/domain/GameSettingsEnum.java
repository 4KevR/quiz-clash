package com.github.quizclash.domain;

public enum GameSettingsEnum implements Displayable {
    CATEGORIES("Categories played per user in one game"),
    BACK("Back to main menu");

    private final String displayName;

    GameSettingsEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
