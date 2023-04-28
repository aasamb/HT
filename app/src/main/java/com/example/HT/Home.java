package com.example.HT;

public class Home extends Storage {
    private static Home home;

    private Home() {
    }

    public static Home getInstance() {
        if (home == null)   {
            home = new Home();
        }
        return home;
    }


    public void createLutemon(Lutemon lutemon)  {
        lutemons.put(lutemon.getId(), lutemon);
        //lutemons.forEach((key, value) -> System.out.println(key + ": " + value.getName() + " (" + value.getType() + ")"));
    }
}
