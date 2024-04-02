package com.github.quizclash.domain;

import java.util.List;

public class InformationScreen extends Screen {
    private final List<String> lines;
    public InformationScreen(String screenName, List<String> lines) {
        super(screenName);
        this.lines = lines;
    }

    public List<String> getLines() {
        return lines;
    }
}
