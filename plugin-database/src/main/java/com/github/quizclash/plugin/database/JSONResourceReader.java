package com.github.quizclash.plugin.database;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSONResourceReader {
  public static JSONArray from(JSONResourceEnum resource) throws IOException {
    ClassLoader classLoader = JSONResourceEnum.class.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(resource.getPath());
    assert inputStream != null;
    StringBuilder lines = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
      while (br.ready()) {
        lines.append(br.readLine());
      }
    }
    return new JSONArray(lines.toString());
  }
}

