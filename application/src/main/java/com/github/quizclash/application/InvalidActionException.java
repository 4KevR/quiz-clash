package com.github.quizclash.application;

public class InvalidActionException extends Exception {
    public InvalidActionException(String errorMessage) {
        super(errorMessage);
    }
}
