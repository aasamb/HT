package com.example.HT;

public class Home extends Storage {
    private static Home home = null;

    private Home() {
    }

    public static Home getInstance() {
        return (home == null) ? new Home() : home;
    }


    public void createLutemon(Lutemon lutemon)  {
        lutemons.put(lutemon.getId(), lutemon);
        lutemons.forEach((key, value) -> System.out.println(key + ": " + value.getName() + " (" + value.getType() + ")"));
    }
}
