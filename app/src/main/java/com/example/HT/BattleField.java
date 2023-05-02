package com.example.HT;

import java.util.ArrayList;

public class BattleField extends Storage {
    private static BattleField battleField;
    private ArrayList<String> battleResult;

    // Private constructor only, because of Singleton principle.
    private BattleField() {
        this.location = Location.BATTLE;
    }

    public static BattleField getInstance() {
        if (battleField == null)   {
            battleField = new BattleField();
        }
        return battleField;
    }

    public ArrayList<String> fight(Lutemon lutemon1, Lutemon lutemon2) {
        battleResult = new ArrayList<>();
        Lutemon attackingLutemon = lutemon1, defendingLutemon = lutemon2;

        while (defendingLutemon.getHealth() > 0)    {

            if (!battleResult.isEmpty())   {   // Counting off the first round.
                battleResult.add(defendingLutemon.getNameAndType() + " manages to escape defeat.");
                battleResult.add("");   // Empty line to make reading fights easier.
                if (attackingLutemon.equals(lutemon1))  {   // Let's switch the Lutemons other way around.
                    attackingLutemon = lutemon2;
                    defendingLutemon = lutemon1;
                }   else {
                    attackingLutemon = lutemon1;
                    defendingLutemon = lutemon2;
                }
            }

            battleResult.add(attackingLutemon.getStatsPrint());
            battleResult.add(defendingLutemon.getStatsPrint());
            battleResult.add(attackingLutemon.getId() + ": " + attackingLutemon.getNameAndType() + " attacks " + defendingLutemon.getId() + ": " + defendingLutemon.getNameAndType());
            defendingLutemon.defense(attackingLutemon);
        }

        // If defending Lutemon experiences health below zero, we end up here.
        battleResult.add(defendingLutemon.getNameAndType() + " is defeated in a fight.");
        // Adding result stats to the individual Lutemons' records.
        defendingLutemon.addOneDefeat();
        defendingLutemon.addOneExperience();    // Defeat is also an experience.
        attackingLutemon.addOneWin();
        attackingLutemon.addOneExperience();

        // When Lutemon loses a fight, it's forced to move back to home to gain full health again.
        Home.getInstance().addLutemon(BattleField.getInstance().takeLutemonAway(defendingLutemon.getId()));

        // Checking if Lutemon gets to live after the fight or not.
        if (defendingLutemon.getDefeats() < 3) {    // Lutemon can lose 2 fights and continue to live.
            battleResult.add(defendingLutemon.getNameAndType() + " continues to live with " + defendingLutemon.getDefeats() + " defeats.");
        }   else {  // Lutemon gets killed permanently when they lose three fights in total.
            //Lutemon killedLutemon = this.takeLutemonAway(defendingLutemon.getId());
            battleResult.add(defendingLutemon.getNameAndType() + " gets killed permanently.");
            Home.getInstance().takeLutemonAway(defendingLutemon.getId());
            //defendingLutemon = killedLutemon = null;   // Let's destroy the object references as well.
        }

        return battleResult;
    }
}

