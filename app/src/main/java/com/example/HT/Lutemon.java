package com.example.HT;

public abstract class Lutemon {
    private static int idCounter = 0;
    protected String name, colorType;
    protected Location location = Location.HOME;
    protected int attack, defense, experience = 0, maxHealth, health, id, wins = 0, defeats = 0, image = 0;

    public Lutemon(String name, int image, int attack, int defense, int maxHealth) {
        this.name = name;
        this.image = image;
        this.attack = attack;
        this.defense = defense;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.id = idCounter++;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public String getColorType() {
        return colorType;
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
