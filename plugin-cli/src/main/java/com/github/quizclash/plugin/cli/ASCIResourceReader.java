package com.github.quizclash.plugin.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ASCIResourceReader {
  public static List<String> from(ResourceEnum resource) throws IOException {
    ClassLoader classLoader = ASCIResourceReader.class.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(resource.getPath());
    assert inputStream != null;
    List<String> lines = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
      while (br.ready()) {
        lines.add(br.readLine());
      }
    }
    return lines;
  }
}
