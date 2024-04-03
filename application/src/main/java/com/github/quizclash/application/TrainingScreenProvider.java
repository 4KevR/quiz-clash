package com.github.quizclash.application;

import com.github.quizclash.domain.*;

import java.util.ArrayList;
import java.util.List;

public class TrainingScreenProvider implements ScreenProvider {
    private final Repository repository;
    private final QuizGame quizGame;
    private boolean hasNextScreen = true;

    public TrainingScreenProvider(Repository repository) {
        this.repository = repository;
        String playerName = this.repository.getUserRepository().getUsers().get(0).getName();
        Player[] trainingPlayer = {new Player(playerName)};
        this.quizGame = new QuizGame(repository.getCategoryRepository(), 4, trainingPlayer);
    }

    public Screen fetchScreen() {
        if (quizGame.isFinished()) {
            List<String> lines = new ArrayList<>();
            Player trainingPlayer = quizGame.getPlayers()[0];
            lines.add(String.format("%s - %d points", trainingPlayer.getPlayerName(), trainingPlayer.getCurrentScore().getIntScore()));
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
        if (quizGame.isFinished()) {
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
