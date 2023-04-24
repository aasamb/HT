package com.example.HT;

public class BattleField extends Storage {

    private static BattleField battleField = null;

    private BattleField() {
    }

    public static BattleField getInstance() {
        return (battleField == null) ? new BattleField() : battleField;
    }

    public void fight() {

    }
}

