package com.github.quizclash.application;

public class TerminationException extends RuntimeException {
  public TerminationException(String errorMessage) {
    super(errorMessage);
  }
}
