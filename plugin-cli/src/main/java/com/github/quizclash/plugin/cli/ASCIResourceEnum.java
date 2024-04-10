package com.github.quizclash.plugin.cli;

import com.github.quizclash.application.Resource;

public enum ASCIResourceEnum implements Resource {
  NAME("asci/name.txt");

  private final String path;

  ASCIResourceEnum(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}
