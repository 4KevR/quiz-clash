package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.OptionScreen;
import com.github.quizclash.application.QuizGame;
import com.github.quizclash.application.screen.Screen;
import com.github.quizclash.application.action.Action;
import com.github.quizclash.application.action.IntegerActionable;
import com.github.quizclash.application.screen.InformationScreen;
import com.github.quizclash.domain.*;

import java.util.ArrayList;
import java.util.List;

public class TrainingScreenProvider implements ScreenProvider, IntegerActionable {
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
