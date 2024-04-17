package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.QuizGame;
import com.github.quizclash.application.screen.OptionScreen;
import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.domain.Player;
import com.github.quizclash.domain.Question;
import com.github.quizclash.domain.Repository;
import com.github.quizclash.domain.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocalMultiplayerScreenProvider implements ScreenProvider {
  private final Repository repository;
  private final ScreenFactory screenFactory;
  private final QuizGame quizGame;

  public LocalMultiplayerScreenProvider(Repository repository, ScreenFactory screenFactory) {
    this.repository = repository;
    this.screenFactory = screenFactory;
    List<User> users = this.repository.getUserRepository().getUsers();
    Collections.shuffle(users);
    Player[] players = new Player[users.size()];
    for (int i = 0; i < users.size(); i++) {
      players[i] = new Player(users.get(i).getName());
    }
    this.quizGame = new QuizGame(repository.getCategoryRepository(),
        repository.getSettingsRepository()
            .getCategoriesPerGameAndUser() * repository.getUserRepository().getUsers().size(),
        players);
  }

  @Override
  public void execute() {
    while (!quizGame.isFinished()) {
      if (quizGame.isSelectingCategory()) {
        String playerName = quizGame.getCurrentPlayer().getPlayerName();
        OptionScreen optionScreen = screenFactory.createOptionScreen(
            playerName + ", select category", quizGame.getRemainingGameCategories());
        optionScreen.render();
        int action = optionScreen.getOptionInput().getActionValue();
        quizGame.setCurrentCategory(action - 1);
      } else {
        Question currentQuestion = quizGame.getCurrentQuestion();
        OptionScreen optionScreen = screenFactory.createOptionScreen(
            "Question: " + currentQuestion.getQuestion(),
            List.of(currentQuestion.getQuestionOptions()));
        optionScreen.render();
        int action = optionScreen.getOptionInput().getActionValue();
        quizGame.submitQuestionAnswer(action - 1);
      }
    }
    List<String> lines = new ArrayList<>();
    Player[] players = quizGame.getPlayers();
    for (Player player : players) {
      lines.add(String.format("%s - %d points", player.getPlayerName(),
          player.getCurrentScore().getIntScore()));
    }
    screenFactory.createInformationScreen("Result", lines).render();
  }

  public ScreenProviderType getNextScreenProviderType() {
    return ScreenProviderType.MENU;
  }
}
