package com.github.quizclash.application;

import com.github.quizclash.application.screen.provider.Helper;
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
    this.repository = Helper.getMockedRepository();
    List<User> users = repository.getUserRepository().getUsers();
    this.players = new Player[users.size()];
    for (int i = 0; i < users.size(); i++) {
      this.players[i] = new Player(users.get(i).getName());
    }
    this.quizGame = new QuizGame(repository.getCategoryRepository(), CATEGORY_AMOUNT, this.players);
  }

  @Test
  void getRemainingGameCategories() {
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
    assertTrue(this.repository.getUserRepository().getUsers().stream().map(User::getName).toList()
        .contains(quizGame.getCurrentPlayer().getPlayerName()));
  }

  @Test
  void isSelectingCategory() {
    assertTrue(quizGame.isSelectingCategory());
    quizGame.setCurrentCategory(0);
    assertFalse(quizGame.isSelectingCategory());
  }

  @Test
  void getPlayers() {
    assertArrayEquals(this.players, quizGame.getPlayers());
  }

  @Test
  void isFinished() {
    assertFalse(quizGame.isFinished());
    while (!quizGame.isFinished()) {
      quizGame.setCurrentCategory(0);
      quizGame.submitQuestionAnswer(0);
    }
    assertTrue(quizGame.isFinished());
  }

  @Test
  void submitQuestionAnswer() {
    quizGame.setCurrentCategory(0);
    quizGame.submitQuestionAnswer(0);
    assertTrue(quizGame.isFinished() || quizGame.isSelectingCategory());
  }
}