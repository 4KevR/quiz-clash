package com.github.quizclash.plugin.cli.screen;

import com.github.quizclash.application.action.Action;
import com.github.quizclash.application.screen.OptionScreen;
import com.github.quizclash.domain.Displayable;
import com.github.quizclash.plugin.cli.CLIWindowManager;

import java.util.List;
import java.util.ListIterator;

public class CLIOptionScreen extends OptionScreen {
  private final CLIWindowManager cliWindow;
  private int userOption;

  public CLIOptionScreen(String screenName,
                         List<? extends Displayable> displayableList,
                         CLIWindowManager cliWindow) {
    super(screenName, displayableList);
    this.cliWindow = cliWindow;
  }

  @Override
  public void render() {
    cliWindow.clearAllCanvas();
    cliWindow.printAnimated(this.getScreenName(), 20);
    cliWindow.moveOnCanvas(0, 2);
    List<? extends Displayable> optionList = this.getScreenOptions();
    userOption = selectFromOptions(optionList);
  }

  @Override
  public Action<Integer> getOptionInput() {
    return new Action<>(userOption);
  }

  private int selectFromOptions(List<? extends Displayable> optionList) {
    ListIterator<? extends Displayable> gameModeListIterator = optionList.listIterator();
    while (gameModeListIterator.hasNext()) {
      cliWindow.println(gameModeListIterator.nextIndex() + 1 + ") " + gameModeListIterator.next()
          .getDisplayName());
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
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          throw new RuntimeException("The application runtime was interrupted: " + e.getMessage());
        }
        cliWindow.clearActionField();
        System.out.print("\u001b[1D");
        System.out.print("\u001b[1A");
      }
    }
    return selected;
  }
}
