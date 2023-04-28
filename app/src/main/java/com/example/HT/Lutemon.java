package com.example.HT;

import android.widget.Toast;

public abstract class Lutemon {
    private static int idCounter = 0;
    protected String name, color;
    protected Location location = Location.HOME;
    protected int attack, defense, experience = 0, maxHealth, health = maxHealth, id, wins = 0, defeats = 0, image;

    public Lutemon(String name, int attack, int defense, int maxHealth) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.maxHealth = maxHealth;
        this.id = idCounter++;
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }

    public static int getNumberOfCreatedLutemons() {
        return idCounter;
    }

    public String getColor() {
        return color;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getExperience() {
        return experience;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getId() {
        return id;
    }

    public int getWins() {
        return wins;
    }

    public int getDefeats() {
        return defeats;
    }

    public void attack() {

    }

    public void defense(Lutemon lutemon) {

    }

    public String getName() {
        return name;
    }

}
