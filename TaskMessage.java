package com.semana14;

public class TaskMessage {

    private final String text;
    private final boolean simulateFailure;

    public TaskMessage(String text, boolean simulateFailure) {
        this.text = text;
        this.simulateFailure = simulateFailure;
    }

    public String getText() {
        return text;
    }

    public boolean shouldSimulateFailure() {
        return simulateFailure;
    }
}
