package com.example.HT;

public abstract class Lutemon {
    // Abstract class, only child classes can be instantiated.

    private static int idCounter = 0;
    protected String name;
    protected Location location;

    // Initializing some of the values to zero.
    protected int maxHealth, id, wins = 0, defeats = 0, image, experience = 0, trainingDays = 0;
    protected double attack, defense, health;

    // Creating max-values to some performance values for easier further development.
    protected static int maxAttack = 10, maxDefense = 5,  maxTrainingDays = 7;

    public Lutemon(String name, int image, double attack, double defense, int maxHealth) {
        this.name = name;
        this.image = image;
        // Adding a bit randomization to attack and defense values.
        this.attack = round(randomizeLutemonValue(attack, 3), 1);
        this.defense = round(randomizeLutemonValue(defense, 2), 1);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.id = idCounter++;
        // All Lutemons are created at HOME.
        this.location = Location.HOME;
    }
    public void setLocation(Location location) {
        this.location = location;

        // When Lutemons gets back at HOME location, health returns always to the max.
        if (location == Location.HOME)  {
            this.health = this.maxHealth;
        }
    }


    // Methods only to add defeats, wins and experience because they shouldn't be reduced.
    public void addOneDefeat() {
        this.defeats++;
    }

    public void addOneExperience()  {
        this.experience++;
    }

    public void addOneWin() {
        this.wins++;
    }



    // Separate methods to train Lutemons' attack and defense skills,
    // no Setters for these values, only improvement.
    public boolean improveAttack(double improveByThis) {
        boolean trainable = trainingDays < maxTrainingDays;
        // It's possible to train 7 times only.
        if (trainable) {
            this.attack = round(this.attack + improveByThis, 1);
            // If the attack is already at the max, no training days are used.
            if (this.attack >= maxAttack)   {
                this.attack = maxAttack;
            }   else {
                trainingDays++;
            }
        }
        // We return the result whether the Lutemon was trained or not.
        return trainable;
    }

    public boolean improveDefense(double improveByThis) {
        boolean trainable = trainingDays < maxTrainingDays;
        // It's possible to train 7 times only.
        if (trainable)  {
            this.defense = round(this.defense + improveByThis, 1);
            // If the defense is already at the max, no training days are used.
            if (this.defense >= maxDefense) {
                this.defense = maxDefense;
            }   else {
                trainingDays++;
            }
        }
        // We return the result whether the Lutemon was trained or not.
        return trainable;
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

    public String getNameAndType()  {
        return this.name + "(" + this.getType() + ")";
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

    // Two Getters into one.
    public String getHealthString() {
        return this.health + "/" + this.maxHealth;
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

    // Method for Fight-functionality.
    public String getStatsPrint()   {
        return "ID " + id + ": " + name + " (" + getType() + ") " + "A: " + attack +
                ";  D: " + defense +
                ";  E: " + experience +
                ";  H: " + getHealthString();
    }

    public int getTrainingDays() {
        return trainingDays;
    }

    public String getName() {
        return name;
    }

    public double attack() {
        return attack;
    }
    // Other Lutemon attacks "into" this method.

    public void defense(Lutemon attackingLutemon) {
        // The defense skill of this Lutemon decreases the damage done by the attack skill of
        // the other Lutemon.
        this.health -= (attackingLutemon.attack() - this.defense);
        this.health = round(this.health, 1);
    }

    // Method inside the class to randomize initial skill values.
    private double randomizeLutemonValue(double lutemonValue, int magnitude)    {
        double newValue = lutemonValue + (Math.random() - 0.5) * magnitude;
        return (newValue < 0) ? 0 : newValue;
    }

    // Static method to round values. Inspiration taken from stackoverflow.com.
    // Is located in this class, because it's used only in this class.
    private static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

}
