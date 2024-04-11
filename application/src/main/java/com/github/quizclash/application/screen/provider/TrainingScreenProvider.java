package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.QuizGame;
import com.github.quizclash.application.screen.OptionScreen;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.domain.Player;
import com.github.quizclash.domain.Question;
import com.github.quizclash.domain.Repository;

import java.util.ArrayList;
import java.util.List;

public class TrainingScreenProvider implements ScreenProvider {
  private final Repository repository;
  private final ScreenFactory screenFactory;
  private final QuizGame quizGame;

  public TrainingScreenProvider(Repository repository, ScreenFactory screenFactory) {
    this.repository = repository;
    this.screenFactory = screenFactory;
    String playerName = this.repository.getUserRepository().getUsers().get(0).getName();
    Player[] trainingPlayer = {new Player(playerName)};
    this.quizGame = new QuizGame(repository.getCategoryRepository(), 4, trainingPlayer);
  }

  @Override
  public void execute() throws InterruptedException {
    while (!quizGame.isFinished()) {
      if (quizGame.isSelectingCategory()) {
        String playerName = quizGame.getCurrentPlayer().getPlayerName();
        OptionScreen optionScreen = screenFactory.createOptionScreen(
            playerName + ", select category", quizGame.getRemainingGameCategories());
        optionScreen.render();
        int actionValue = optionScreen.getOptionInput().getActionValue();
        quizGame.setCurrentCategory(actionValue - 1);
      } else {
        Question currentQuestion = quizGame.getCurrentQuestion();
        OptionScreen optionScreen = screenFactory.createOptionScreen(
            "Question: " + currentQuestion.getQuestion(),
            List.of(currentQuestion.getQuestionOptions()));
        optionScreen.render();
        int actionValue = optionScreen.getOptionInput().getActionValue();
        quizGame.submitQuestionAnswer(actionValue - 1);
      }
    }
    List<String> lines = new ArrayList<>();
    Player trainingPlayer = quizGame.getPlayers()[0];
    lines.add(String.format("%s - %d points", trainingPlayer.getPlayerName(),
        trainingPlayer.getCurrentScore().getIntScore()));
    screenFactory.createInformationScreen("Result", lines).render();
  }

  public ScreenProviderType getNextScreenProviderType() {
    return ScreenProviderType.MENU;
  }
}
