package com.github.quizclash.plugin.cli;

import com.github.quizclash.application.InvalidActionException;
import com.github.quizclash.application.ScreenProviderManager;
import com.github.quizclash.domain.*;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

public class QuizClashCLI {
  private final CLIWindowManager cliWindow;
  private final ScreenProviderManager screenProviderManager;

  public QuizClashCLI(int sizeX, int sizeY, Repository repository) throws InterruptedException, IOException {
    this.cliWindow = new CLIWindowManager(sizeX, sizeY);
    this.screenProviderManager = new ScreenProviderManager(repository);
    cliWindow.printAnimated("Welcome to ...", 50);
    Thread.sleep(500);
    cliWindow.clearAllCanvas();
    List<String> nameLines = ASCIResourceReader.from(ASCIResourceEnum.NAME);
    cliWindow.printLines(nameLines);
    Thread.sleep(2000);
  }

  public void run() throws InterruptedException, InvalidActionException {
    ScreenProvider currentScreenProvider;
    while ((currentScreenProvider = screenProviderManager.getCurrentScreenProvider()) != null) {
      while (currentScreenProvider.hasNextScreen()) {
        render(currentScreenProvider.fetchScreen());
      }
      screenProviderManager.updateScreenProvider();
    }
  }

  public void destroy() throws InterruptedException {
    cliWindow.clearAllCanvas();
    cliWindow.printAnimated("Thank you for playing QuizClash!", 50);
    Thread.sleep(2000);
    cliWindow.clearCLI();
  }

  private void render(Screen screen) throws InterruptedException, InvalidActionException {
    cliWindow.clearAllCanvas();
    cliWindow.printAnimated(screen.getScreenName(), 20);
    cliWindow.moveOnCanvas(0, 2);
    if (screen instanceof OptionScreen optionScreen) {
      List<? extends Displayable> optionList = optionScreen.getScreenOptions();
      Action<Integer> integerAction =  selectFromOptions(optionList);
      screenProviderManager.submitIntegerAction(integerAction);
    } else if (screen instanceof TextInputScreen textInputScreen) {
      Action<String> stringAction = enterTextFromRequest(textInputScreen.getInputRequest());
      screenProviderManager.submitStringAction(stringAction);
    } else if (screen instanceof InformationScreen informationScreen) {
      for (String line : informationScreen.getLines()) {
        cliWindow.println(line);
      }
      cliWindow.moveToActionField();
      cliWindow.waitForEnter();
    }
  }

  private Action<Integer> selectFromOptions(List<? extends Displayable> optionList) throws InterruptedException {
    ListIterator<? extends Displayable> gameModeListIterator = optionList.listIterator();
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
    return new Action<>(selected);
  }

  private Action<String> enterTextFromRequest(String request) {
    cliWindow.moveToActionField();
    return new Action<>(cliWindow.getTextInput(request));
  }
}
