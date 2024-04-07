package com.github.quizclash.domain;

import java.io.Serializable;

public class Action<T extends Serializable> {
    private final T actionValue;

    public Action(T actionValue) {
        this.actionValue = actionValue;
    }

    public T getActionValue() {
        return actionValue;
    }
}
