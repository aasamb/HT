package com.example.HT;

public class TrainingArea extends Storage {

    private static TrainingArea trainingArea;

    private TrainingArea() {
        this.location = Location.TRAINING;
    }

    public static TrainingArea getInstance() {
        if (trainingArea == null)   {
            trainingArea = new TrainingArea();
        }
        return trainingArea;
    }

    public void train() {

    }
}
