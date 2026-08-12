package com.semana14;

/**
 * Mensaje que transporta una tarea hacia los workers.
 */
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
