package com.example.HT;

public abstract class Lutemon {
    private static int idCounter = 0;
    protected String name;
    protected Location location;
    protected int maxHealth, id, wins = 0, defeats = 0, image, experience = 0, trainingDays = 0;
    protected double attack, defense, health;
    protected static int maxAttack = 10, maxDefense = 5,  maxTrainingDays = 7;

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

    private void setExperience(int experience) {
        this.experience = experience;
    }

    private void setHealth(int health) {
        this.health = health;
    }

    private void setWins(int wins) {
        this.wins = wins;
    }

    private void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    public void addOneDefeat() {
        this.defeats++;
    }

    public void addOneExperience()  {
        this.experience++;
    }

    public void addOneWin() {
        this.wins++;
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

    public double getDefense() {
        return defense;
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

    public double getExperience() {
        return experience;
    }

    public double getHealth() {
        return health;
    }

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

    public String getStatsPrint()   {
        StringBuilder sbStats = new StringBuilder("ID " + id + ": " + name + " (" + getType() + ") ");
        sbStats.append("A: ").append(attack);
        sbStats.append(";  D: ").append(defense);
        sbStats.append(";  E: ").append(experience);
        sbStats.append(";  H: ").append(getHealthString());
        return sbStats.toString();
    }

    public int getTrainingDays() {
        return trainingDays;
    }

    public double attack() {
        return attack;
    }

    public void defense(Lutemon attackingLutemon) {
        //System.out.print("ID: " + id + ", " + name + " (" + getType() + ") " + " H: " + health + ", Opponent A: " + attackingLutemon.getAttack());
        this.health -= (attackingLutemon.attack() - this.defense);
        this.health = round(this.health, 1);
        //System.out.println( "; aftet attack H: " + health);
    }

    public String getName() {
        return name;
    }

    private double randomizeLutemonValue(double lutemonValue, int magnitude)    {
        double newValue = lutemonValue + (Math.random() - 0.5) * magnitude;
        return (newValue < 0) ? 0 : newValue;
    }

    private static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

}
