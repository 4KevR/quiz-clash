package com.github.quizclash.plugin.database;

import com.github.quizclash.application.Resource;

public enum JSONResourceEnum implements Resource {
  CATEGORIES("categories.json");

  private final String path;

  JSONResourceEnum(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}
