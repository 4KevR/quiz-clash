package com.github.quizclash.application;

import com.github.quizclash.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuizGame {
    private final Category[] gameCategories;
    private final Player[] players;
    private final List<Integer> playedCategoryIds = new ArrayList<>();
    private boolean isSelectingCategory = true;
    private Question currentQuestion;
    private int indexOfCurrentPlayer;
    private boolean finished = false;

    public QuizGame(CategoryRepository categoryRepository, int amountOfCategories, Player[] players) {
        this.gameCategories = categoryRepository.getRandomCategories(amountOfCategories);
        this.players = players;
    }

    public List<? extends Displayable> getRemainingGameCategories() {
        return Arrays.stream(gameCategories).filter(category -> !playedCategoryIds.contains(category.getId())).collect(Collectors.toList());
    }

    public void setCurrentCategory(int newCategoryIndex) {
        Category currentCategory = (Category) this.getRemainingGameCategories().get(newCategoryIndex);
        playedCategoryIds.add(currentCategory.getId());
        isSelectingCategory = false;
        currentQuestion = currentCategory.getRandomQuestion();
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public Player getCurrentPlayer() {
        return players[indexOfCurrentPlayer];
    }

    public boolean isSelectingCategory() {
        return isSelectingCategory;
    }

    public Player[] getPlayers() {
        return players;
    }

    public boolean isFinished() {
        return finished;
    }

    public void submitQuestionAnswer(int answerIndex) {
        boolean questionWasRight = currentQuestion.checkAnswer(answerIndex);
        if (questionWasRight) {
            players[indexOfCurrentPlayer].incrementScore(new Score(500));
        }
        indexOfCurrentPlayer = (indexOfCurrentPlayer + 1) % players.length;
        if (gameCategories.length != playedCategoryIds.size()) {
            isSelectingCategory = true;
        } else {
            finished = true;
        }
    }
}
