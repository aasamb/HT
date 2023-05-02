package com.example.HT;

import java.util.ArrayList;

public class BattleField extends Storage {
    private static BattleField battleField;
    private ArrayList<String> battleResult;
    private BattleField() {
        this.location = Location.BATTLE;
    }

    public static BattleField getInstance() {
        if (battleField == null)   {
            battleField = new BattleField();
        }
        return battleField;
    }

    public void fight() {

    }

    public String singleFight(Lutemon attackingLutemon, Lutemon defendingLutemon)   {
        String result = "";

        return result;
    }
}

