package com.example.HT;

public abstract class Lutemon {
    private static int idCounter = 0;
    protected String name;
    protected Location location;
    protected int maxHealth, id, wins = 0, defeats = 0, image;
    protected double attack, defense, experience = 0, health;
    protected static int maxAttack = 10, maxDefense = 5;

    public Lutemon(String name, int image, double attack, double defense, int maxHealth) {
        this.name = name;
        this.image = image;
        this.attack = round(randomizeLutemonValue(attack, 3), 1);
        this.defense = round(randomizeLutemonValue(defense, 2), 1);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.id = idCounter++;
        this.location = Location.HOME;
    }


    public static int getMaxAttack() {
        return maxAttack;
    }

    public static int getMaxDefense() {
        return maxDefense;
    }

    public void setLocation(Location location) {
        this.location = location;

        // When Lutemons gets back at HOME location, health returns always to the max.
        if (location == Location.HOME)  {
            this.health = this.maxHealth;
        }
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    public Location getLocation() {
        return location;
    }

    public int getImage() {
        return image;
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }

    public static int getNumberOfCreatedLutemons() {
        return idCounter;
    }

    public double getAttack() {
        return attack;
    }

    public double getDefense() {
        return defense;
    }

    public double getExperience() {
        return experience;
    }

    public double getHealth() {
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

    public double attack() {

        return attack;
    }

    public void defense(Lutemon attackingLutemon) {
        System.out.print("ID: " + id + ", " + name + " (" + getType() + ") " + " health: " + health + ", attacking value: " + attackingLutemon.getAttack());
        health -= attackingLutemon.attack();
        System.out.println( "; health after" + health);
    }

    public String getName() {
        return name;
    }

    private double randomizeLutemonValue(double lutemonValue, int magnitude)    {
        return lutemonValue + (Math.random() - 0.5) * magnitude;
    }

    private static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

}
