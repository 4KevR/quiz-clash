package com.github.quizclash.application.screen.provider;

import com.github.quizclash.application.screen.ScreenFactory;
import com.github.quizclash.domain.InvalidQuestionFormatException;
import com.github.quizclash.domain.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrainingScreenProviderTest {

  private TrainingScreenProvider trainingScreenProvider;

  @BeforeEach
  void setUp() throws InvalidQuestionFormatException {
    Repository repository = Helper.getMockedRepository();
    ScreenFactory screenFactory = Mockito.mock(ScreenFactory.class);
    this.trainingScreenProvider = new TrainingScreenProvider(repository, screenFactory);
  }

  @Test
  void getNextScreenProviderType() {
    assertEquals(ScreenProviderType.MENU, this.trainingScreenProvider.getNextScreenProviderType());
  }
}