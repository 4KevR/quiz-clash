package com.github.quizclash.application.screen;

import com.github.quizclash.domain.Displayable;

import java.util.List;

public interface ScreenFactory {
  InformationScreen createInformationScreen(String screenName, List<String> lines);
  NumberInputScreen createNumberInputScreen(String screenName, String inputRequest);
  OptionScreen createOptionScreen(String screenName, List<? extends Displayable> displayableList);
  TextInputScreen createTextInputScreen(String screenName, String inputRequest);
}
