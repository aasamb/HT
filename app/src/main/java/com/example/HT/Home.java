package com.example.HT;

public class Home extends Storage {

    private static Home home;

    // Private constructor only because of Singleton principle.
    private Home() {
        this.location = Location.HOME;
    }

    public static Home getInstance() {
        if (home == null)   {
            home = new Home();
        }
        return home;
    }


    // Uniquely named method for HOME class only. Availability to more development,
    // no differences to Storage.addLutemon() at the moment.
    public void createLutemon(Lutemon lutemon)  {
        lutemons.put(lutemon.getId(), lutemon);
        String print = "Home: " + lutemon.getClass().getSimpleName() + "-Lutemon " + lutemon.getName() + " luotu!";
        //System.out.println(print);
        //Toast.makeText(, print, Toast.LENGTH_SHORT).show();
        //lutemons.forEach((key, value) -> System.out.println(key + ": " + value.getName() + " (" + value.getType() + ")"));
    }

}
