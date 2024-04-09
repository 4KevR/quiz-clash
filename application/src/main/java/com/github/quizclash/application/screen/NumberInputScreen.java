package com.github.quizclash.application.screen;

public class NumberInputScreen extends Screen {
    private final String inputRequest;
    public NumberInputScreen(String screenName, String inputRequest) {
        super(screenName);
        this.inputRequest = inputRequest;
    }

    public String getInputRequest() {
        return this.inputRequest;
    }
}
