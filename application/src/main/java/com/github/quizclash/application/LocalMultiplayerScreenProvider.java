package com.github.quizclash.application;

import com.github.quizclash.domain.*;

import java.util.ArrayList;
import java.util.List;

public class LocalMultiplayerScreenProvider implements ScreenProvider, IntegerActionable {
    private final Repository repository;
    private final QuizGame quizGame;
    private boolean hasNextScreen = true;

    public LocalMultiplayerScreenProvider(Repository repository) {
        this.repository = repository;
        List<User> users = this.repository.getUserRepository().getUsers();
        Player[] players = new Player[users.size()];
        for(int i = 0; i < users.size(); i++) {
            players[i] = new Player(users.get(i).getName());
        }
        this.quizGame = new QuizGame(repository.getCategoryRepository(), 4, players);
    }

    public Screen fetchScreen() {
        if(quizGame.isFinished()){
            List<String> lines = new ArrayList<>();
            Player[] players = quizGame.getPlayers();
            for(Player player : players) {
                lines.add(String.format("%s - %d points", player.getPlayerName(), player.getCurrentScore().getIntScore()));
            }
            hasNextScreen = false;
            return new InformationScreen("Result", lines);
        } else if (quizGame.isSelectingCategory()) {
            String playerName = quizGame.getCurrentPlayer().getPlayerName();
            return new OptionScreen(playerName + ", select category", quizGame.getRemainingGameCategories());
        } else {
            Question currentQuestion = quizGame.getCurrentQuestion();
            return new OptionScreen("Question: " + currentQuestion.getQuestion(), List.of(currentQuestion.getQuestionOptions()));
        }
    }

    public void submitAction(Action<Integer> action) {
        int actionValue = action.getActionValue();
        if (quizGame.isSelectingCategory()) {
            quizGame.setCurrentCategory(actionValue - 1);
        } else {
            quizGame.submitQuestionAnswer(actionValue - 1);
        }
    }

    public ScreenProviderType getNextScreenProviderType() {
        return ScreenProviderType.MENU;
    }

    public boolean hasNextScreen() {
        return hasNextScreen;
    }
}
