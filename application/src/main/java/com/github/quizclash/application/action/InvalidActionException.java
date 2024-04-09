package com.github.quizclash.application.action;

public class InvalidActionException extends Exception {
    public InvalidActionException(String errorMessage) {
        super(errorMessage);
    }
}
