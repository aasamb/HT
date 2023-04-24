package com.example.HT;

public class TrainingArea extends Storage {

    private static TrainingArea trainingArea = null;

    private TrainingArea() {
    }

    public static TrainingArea getInstance() {
        return (trainingArea == null) ? new TrainingArea() : trainingArea;
    }

    public void train() {

    }
}
