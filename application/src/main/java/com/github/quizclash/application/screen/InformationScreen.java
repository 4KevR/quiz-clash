package com.github.quizclash.application.screen;

import java.util.List;

public abstract class InformationScreen extends Screen {
    private final List<String> lines;
    public InformationScreen(String screenName, List<String> lines) {
        super(screenName);
        this.lines = lines;
    }

    public List<String> getLines() {
        return lines;
    }
}
