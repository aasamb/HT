package com.example.HT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public ArrayList<String> train(HashMap<Integer, Lutemon> lutemonsToTrain, boolean attack) {
        double factor;
        boolean trained;
        ArrayList<String> results = new ArrayList<>();
        results.add(( attack ? "Hyökkäystä kehitetty: " : "Puolustusta kehitetty: "));
        for (Lutemon lutemon : lutemonsToTrain.values()) {

            // Let's set 20 % chance the factor is above 1.
            factor = Math.random() * 1.25;
            if (attack) { // Only two possibilities to improve, let's handle it with a boolean variable.

                // Let's raise the factor to the power of 2, while there's 20 % chance this power
                // further increases the value to improve the performance value by, and 80 % chance
                // the power decreases this improvement. So there's rather small chance to get
                // much better than median day in training, and great chance to get close to median
                // day. Theoretical max improvement a day is 1.4
                // and at the halfway lies improvement of 0.4 rounded to one decimal place.
                trained = lutemon.improveAttack(Math.pow(factor, 2));
            }   else {  // Improving defense.
                // Let's multiply the value by 0.8 to decrease the effect since
                // defense is smaller value by default.
                trained = lutemon.improveDefense((Math.pow(factor, 2) * 0.8));
            }
            // Let's make the print for this Lutemon
            String printLine = lutemon.getId() + ": " + lutemon.getNameAndType() + ";  Attack: " + lutemon.getAttack() + ";  Defense: " + lutemon.getDefense() + "; Days trained: " + lutemon.getTrainingDays() + "." + (trained ? "" : "\n" + lutemon.getId() + ": (" + lutemon.getType() + ") Treenipäiviä ei ole käytettävissä enää!");
            results.add(printLine);

        }
        return results;
    }
}
