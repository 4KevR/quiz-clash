package com.github.quizclash.domain;

public class InvalidQuestionFormatException extends Exception {
  public InvalidQuestionFormatException(String errorMessage) {
    super(errorMessage);
  }
}
