package com.github.quizclash.application;

import com.github.quizclash.domain.*;

import java.util.ArrayList;
import java.util.List;

public class LocalMultiplayerScreenProvider implements ScreenProvider {
    private final Repository repository;
    private final QuizGame quizGame;
    private boolean hasNextScreen = true;

    public LocalMultiplayerScreenProvider(Repository repository) {
        this.repository = repository;
        Player[] players = {new Player("Player 1"), new Player("Player 2")};
        this.quizGame = new QuizGame(repository.getCategoryRepository(),4, players);
    }

    public Screen fetchScreen() {
        if(quizGame.isFinished()){
            List<String> lines = new ArrayList<>();
            Player[] players = quizGame.getPlayers();
            for(Player player : players) {
                lines.add(String.format("%s - %d points", player.getPlayerName(), player.getCurrentScore().getIntScore()));
            }
            return new InformationScreen("Result", lines);
        } else if (quizGame.isSelectingCategory()) {
            String playerName = quizGame.getCurrentPlayer().getPlayerName();
            return new OptionScreen(playerName + ", select category", quizGame.getRemainingGameCategories());
        } else {
            Question currentQuestion = quizGame.getCurrentQuestion();
            return new OptionScreen("Question: " + currentQuestion.getQuestion(), List.of(currentQuestion.getQuestionOptions()));
        }
    }

    public void submitAction(Actionable<?> action) {
        int actionValue = (int) action.getActionValue();
        if(quizGame.isFinished()) {
            hasNextScreen = false;
        } else if (quizGame.isSelectingCategory()) {
            quizGame.setCurrentCategory(actionValue - 1);
        } else {
            quizGame.submitQuestionAnswer(actionValue - 1);
        }
    }

    public ScreenProvider getNextScreenProvider() {
        return new MenuScreenProvider(repository);
    }

    public boolean hasNextScreen() {
        return hasNextScreen;
    }
}