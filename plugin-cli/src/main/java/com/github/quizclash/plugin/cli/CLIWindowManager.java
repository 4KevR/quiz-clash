package com.github.quizclash.plugin.cli;

import java.util.List;
import java.util.Scanner;

public class CLIWindowManager {
  private final int sizeX;
  private final int canvasSizeX;
  private final int canvasSizeY;
  private final int actionCanvasSizeY = 2;
  private final int boundaryOffset = 1;
  private final Scanner scanner = new Scanner(System.in);
  private int currentX = 0;
  private int currentY = 0;
  private boolean onCanvas = true;

  public CLIWindowManager(int sizeX, int sizeY) {
    this.sizeX = sizeX;
    canvasSizeX = sizeX - boundaryOffset * 2 - 2;
    canvasSizeY = sizeY - actionCanvasSizeY - boundaryOffset * 4 - 3;
    this.setup();
  }

  public void print(String text) {
    int newX = currentX + text.length();
    while (newX > canvasSizeX) {
      final int remainingLineLength = canvasSizeX - currentX;
      System.out.print(text.substring(0, remainingLineLength));
      text = text.substring(remainingLineLength);
      newX -= canvasSizeX;
      this.addNewLine();
    }
    System.out.print(text);
    currentX = newX;
  }

  public void printAnimated(String text, int delay) throws InterruptedException {
    for (char c : text.toCharArray()) {
      this.print(String.valueOf(c));
      Thread.sleep(delay);
    }
  }

  public void println(String text) {
    this.print(text);
    this.addNewLine();
  }

  public void printLines(List<String> textLines) {
    for (String text : textLines) {
      this.println(text);
    }
  }

  public void moveOnCanvas(int moveToX, int moveToY) {
    System.out.print("\u001b[1000D");
    System.out.print("\u001b[1000A");
    System.out.printf("\u001b[%dC", moveToX + boundaryOffset + 1);
    currentX = moveToX;
    System.out.printf("\u001b[%dB", moveToY + boundaryOffset + 1);
    currentY = moveToY;
    onCanvas = true;
  }

  public void moveOnActionField(int moveToX, int moveToY) {
    if (onCanvas)
      moveToActionField();
    System.out.printf("\u001b[%dD", currentX);
    System.out.printf("\u001b[%dA", currentY);
    System.out.printf("\u001b[%dC", moveToX);
    currentX = moveToX;
    System.out.printf("\u001b[%dB", moveToY);
    currentY = moveToY;
  }

  public void moveToActionField() {
    System.out.printf("\u001b[%dD", currentX - 1);
    System.out.printf("\u001b[%dB", canvasSizeY - currentY + boundaryOffset * 2 + 1);
    onCanvas = false;
    currentX = currentY = 0;
  }

  public void clearAllCanvas() {
    this.moveOnCanvas(0, 0);
    System.out.print("\u001b[1D");
    this.print(" ".repeat(canvasSizeX * canvasSizeY));
    this.clearActionField();
    this.moveOnCanvas(0, 0);
  }

  public void clearActionField() {
    this.moveOnCanvas(0, 0);
    this.moveToActionField();
    System.out.print("\u001b[1D");
    this.print(" ".repeat(canvasSizeX * actionCanvasSizeY));
    this.moveOnActionField(0, 0);
  }

  public String getTextInput(String request) {
    this.print(request + ": ");
    return scanner.nextLine();
  }

  public int getRangeSelect(int lowerBound, int higherBound) {
    this.print(String.format("Select option (%d - %d): ", lowerBound, higherBound));
    if (scanner.hasNextInt()) {
      int selectedOption = scanner.nextInt();
      scanner.nextLine();
      return selectedOption;
    }
    scanner.nextLine();
    return 0;
  }

  public void addNewLine() {
    if (currentY + 1 >= (onCanvas ? canvasSizeY : actionCanvasSizeY)) {
      this.clearAllCanvas();
    } else {
      System.out.println();
      System.out.printf("\u001b[%dC", boundaryOffset + 1);
      currentY += 1;
      currentX = 0;
    }
  }

  public void clearCLI() {
    System.out.print("\u001b[1000D");
    System.out.print("\u001b[1000A");
    System.out.print("\u001b[2J");
  }

  private void setup() {
    this.clearCLI();
    System.out.println("┌" + "─".repeat(sizeX - 2) + "┐");
    for (int i = 0; i < canvasSizeY + boundaryOffset * 2; i++) {
      System.out.println("│" + " ".repeat(sizeX - 2) + "│");
    }
    System.out.println("├" + "─".repeat(sizeX - 2) + "┤");
    for (int i = 0; i < actionCanvasSizeY + boundaryOffset * 2; i++) {
      System.out.println("│" + " ".repeat(sizeX - 2) + "│");
    }
    System.out.println("└" + "─".repeat(sizeX - 2) + "┘");
    this.moveOnCanvas(0, 0);
  }
}
