package com.github.quizclash.domain;

public class Player {
    private final String playerName;
    private Score currentScore;

    public Player(String playerName) {
        this.playerName = playerName;
        currentScore = new Score(0);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void incrementScore(Score scoreToAdd) {
        currentScore = new Score(currentScore.getIntScore() + scoreToAdd.getIntScore());
    }

    public Score getCurrentScore() {
        return currentScore;
    }
}
