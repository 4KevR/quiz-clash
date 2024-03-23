package com.github.quizclash.plugin.cli;

import com.github.quizclash.application.WelcomeScreenProvider;
import com.github.quizclash.domain.*;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

public class QuizClashCLI {
  private final CLIWindowManager cliWindow;
  private ScreenProvider currentScreenProvider;

  public QuizClashCLI(int sizeX, int sizeY, Repository repository) throws InterruptedException, IOException {
    this.cliWindow = new CLIWindowManager(sizeX, sizeY);
    this.currentScreenProvider = new WelcomeScreenProvider(repository);
    cliWindow.printAnimated("Welcome to ...", 50);
    Thread.sleep(500);
    cliWindow.clearAllCanvas();
    List<String> nameLines = ASCIResourceReader.from(ASCIResourceEnum.NAME);
    cliWindow.printLines(nameLines);
    Thread.sleep(2000);
  }

  public void run() throws InterruptedException {
    while (currentScreenProvider != null) {
      while (currentScreenProvider.hasNextScreen()) {
        Actionable<?> action = render(currentScreenProvider.fetchScreen());
        currentScreenProvider.submitAction(action);
      }
      currentScreenProvider = currentScreenProvider.getNextScreenProvider();
    }
  }

  public void destroy() throws InterruptedException {
    cliWindow.clearAllCanvas();
    cliWindow.printAnimated("Thank you for playing QuizClash!", 50);
    Thread.sleep(2000);
    cliWindow.clearCLI();
  }

  private Actionable<?> render(Screen screen) throws InterruptedException {
    cliWindow.clearAllCanvas();
    cliWindow.printAnimated(screen.getScreenName(), 20);
    cliWindow.moveOnCanvas(0, 2);
    if (screen instanceof OptionScreen optionScreen) {
      List<Displayable> optionList = optionScreen.getScreenOptions();
      return selectFromOptions(optionList);
    } else if (screen instanceof TextInputScreen textInputScreen) {
      return enterTextFromRequest(textInputScreen.getInputRequest());
    } else if (screen instanceof InformationScreen informationScreen) {
      for (String line : informationScreen.getLines()) {
        cliWindow.println(line);
      }
      cliWindow.moveToActionField();
      cliWindow.waitForEnter();
    }
    return new IntegerAction(0);
  }

  private Actionable<?> selectFromOptions(List<Displayable> optionList) throws InterruptedException {
    ListIterator<Displayable> gameModeListIterator = optionList.listIterator();
    while (gameModeListIterator.hasNext()) {
      cliWindow.println(gameModeListIterator.nextIndex() + 1 + ") " +
          gameModeListIterator.next().getDisplayName());
    }
    cliWindow.moveToActionField();
    int selected = 0;
    while (selected == 0) {
      selected = cliWindow.getRangeSelect(1, optionList.size());
      if (selected == 0) {
        cliWindow.clearActionField();
        System.out.print("\u001b[1D");
        System.out.print("\u001b[1A");
        cliWindow.print("Please only enter numeric values!");
        Thread.sleep(2000);
        cliWindow.clearActionField();
        System.out.print("\u001b[1D");
        System.out.print("\u001b[1A");
      }
    }
    return new IntegerAction(selected);
  }

  private Actionable<?> enterTextFromRequest(String request) {
    cliWindow.moveToActionField();
    return new TextAction(cliWindow.getTextInput(request));
  }
}
