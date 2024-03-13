package com.github.quizclash.plugin.cli;

public enum ResourceEnum {
  NAME("asci/name.txt");

  private final String path;

  ResourceEnum(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}
