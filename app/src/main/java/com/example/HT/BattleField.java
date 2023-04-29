package com.example.HT;

public class BattleField extends Storage {

    private static BattleField battleField;

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
}

