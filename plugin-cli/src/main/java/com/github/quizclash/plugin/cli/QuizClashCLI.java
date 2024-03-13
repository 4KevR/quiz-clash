package com.github.quizclash.plugin.cli;

import com.github.quizclash.domain.Displayable;
import com.github.quizclash.domain.GameModeEnum;
import com.github.quizclash.domain.MainMenuEnum;
import com.github.quizclash.domain.User;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

public class QuizClashCLI {
  private final CLIWindowManager cliWindow;

  public QuizClashCLI(int sizeX, int sizeY) throws InterruptedException, IOException {
    cliWindow = new CLIWindowManager(sizeX, sizeY);
    cliWindow.printAnimated("Welcome to ...", 50);
    Thread.sleep(500);
    cliWindow.clearCanvas();
    List<String> nameLines = ASCIResourceReader.from(ResourceEnum.NAME);
    cliWindow.printLines(nameLines);
  }

  public String getUserName() {
    cliWindow.moveToActionField();
    return cliWindow.getTextInput("Enter your name");
  }

  public MainMenuEnum selectFromMainMenu(User user) throws InterruptedException {
    cliWindow.clearCanvas();
    cliWindow.printAnimated("Hello " + user.getName() + "!", 20);
    cliWindow.moveOnCanvas(0, 3);
    cliWindow.println("Main Menu");
    cliWindow.addNewLine();
    List<Displayable> mainMenuEnumList = List.of(MainMenuEnum.values());
    return (MainMenuEnum) selectFromOptions(mainMenuEnumList);
  }

  public GameModeEnum selectGame() throws InterruptedException {
    cliWindow.clearCanvas();
    cliWindow.printAnimated("Chose game mode", 20);
    cliWindow.moveOnCanvas(0, 2);
    List<Displayable> gameModeEnumList = List.of(GameModeEnum.values());
    return (GameModeEnum) selectFromOptions(gameModeEnumList);
  }

  public void destroy() throws InterruptedException {
    cliWindow.clearCanvas();
    cliWindow.printAnimated("Thank you for playing QuizClash!", 50);
    Thread.sleep(2000);
    cliWindow.clearCLI();
  }

  private Displayable selectFromOptions(List<Displayable> optionList) {
    ListIterator<Displayable> gameModeListIterator = optionList.listIterator();
    while (gameModeListIterator.hasNext()) {
      cliWindow.println(gameModeListIterator.nextIndex() + 1 + ") " +
          gameModeListIterator.next().getDisplayName());
    }
    cliWindow.moveToActionField();
    int selected = cliWindow.getRangeSelect(1, optionList.size());
    return optionList.get(selected - 1);
  }
}
