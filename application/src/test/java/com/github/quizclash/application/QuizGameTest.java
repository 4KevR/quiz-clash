package com.github.quizclash.application;

import com.github.quizclash.domain.InvalidQuestionFormatException;
import com.github.quizclash.domain.Player;
import com.github.quizclash.domain.Repository;
import com.github.quizclash.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuizGameTest {
  private static final int CATEGORY_AMOUNT = 1;
  private Repository repository;
  private Player[] players;
  private QuizGame quizGame;

  @BeforeEach
  void setUp() throws InvalidQuestionFormatException {
    repository = Helper.getMockedRepository();
    List<User> users = repository.getUserRepository().getUsers();
    players = new Player[users.size()];
    for (int i = 0; i < users.size(); i++) {
      players[i] = new Player(users.get(i).getName());
    }
    quizGame = new QuizGame(repository.getCategoryRepository(), CATEGORY_AMOUNT, players);
  }

  @Test
  void testInitialRemainingGameCategories() {
    assertEquals(CATEGORY_AMOUNT, quizGame.getRemainingGameCategories().size());
  }

  @Test
  void setCurrentCategory() {
    quizGame.setCurrentCategory(0);
    assertEquals(CATEGORY_AMOUNT - 1, quizGame.getRemainingGameCategories().size());
  }

  @Test
  void getCurrentQuestion() {
    assertNull(quizGame.getCurrentQuestion());
    quizGame.setCurrentCategory(0);
    assertNotNull(quizGame.getCurrentQuestion());
  }

  @Test
  void getCurrentPlayer() {
    List<String> usernames = repository
        .getUserRepository()
        .getUsers()
        .stream()
        .map(User::getName)
        .toList();
    String currentPlayerName = quizGame.getCurrentPlayer().getPlayerName();
    assertTrue(usernames.contains(currentPlayerName));
  }

  @Test
  void isSelectingCategory() {
    assertTrue(quizGame.isSelectingCategory());
    quizGame.setCurrentCategory(0);
    assertFalse(quizGame.isSelectingCategory());
  }

  @Test
  void getPlayers() {
    assertArrayEquals(players, quizGame.getPlayers());
  }

  @Test
  void isFinished() {
    assertFalse(quizGame.isFinished());
    for (int i = 0; i < CATEGORY_AMOUNT; i++) {
      quizGame.setCurrentCategory(0);
      quizGame.submitQuestionAnswer(0);
    }
    assertTrue(quizGame.isFinished());
  }

  void submitQuestionAnswer(boolean isRight) {
    Player currentPlayer = quizGame.getCurrentPlayer();
    int currentPlayerScore = currentPlayer.getCurrentScore().getIntScore();
    quizGame.setCurrentCategory(0);
    for (int i = 0; i < quizGame.getCurrentQuestion().getQuestionOptions().length; i++) {
      if (isRight && quizGame.getCurrentQuestion().checkAnswer(i) || !isRight && !quizGame
          .getCurrentQuestion()
          .checkAnswer(i)) {
        quizGame.submitQuestionAnswer(i);
        break;
      }
    }
    assertTrue(quizGame.isFinished() || quizGame.isSelectingCategory());
    assertEquals(currentPlayerScore + (isRight ? 500 : 0),
        currentPlayer.getCurrentScore().getIntScore());
  }

  @Test
  void submitRightQuestionAnswer() {
    submitQuestionAnswer(true);
  }

  @Test
  void submitWrongQuestionAnswer() {
    submitQuestionAnswer(false);
  }
}