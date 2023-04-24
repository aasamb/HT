package com.example.HT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Storage {

    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    private static Storage storage = null;

    private Storage() {}

    public static Storage getInstance() {
        if(storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    public void addItem(Lutemon lutemon)    {
        lutemons.add(lutemon);
    }

    public void removeItem(int index)  {
        lutemons.remove(index);
    }

    /*public void setItem(int index, Lutemon item)   {
        lutemons.set(index, item);
    }*/

    public void editItemDetails(int index, String details)  {
       lutemons.get(index).setDetails(details);
    }

    public ArrayList<Lutemon> getItems() {
        return lutemons;
    }

    public void sortAlphabet()  {
        Collections.sort(lutemons, Comparator.comparing(Lutemon::getDetails).thenComparing(Lutemon::getDate));
    }

    public void sortRecent()  {
        Collections.sort(lutemons, Comparator.comparing(Lutemon::getDate).thenComparing(Lutemon::getDetails));
    }



    public String getStarredString()   {

        StringBuilder sbStarred = new StringBuilder();
        ArrayList<Lutemon> itemsTemporary = lutemons;
        Collections.sort(itemsTemporary, Comparator.comparing(Lutemon::getDate).thenComparing(Lutemon::getDetails));

        for (Lutemon lutemon : itemsTemporary) {
            if (lutemon.isStarred())   {
                sbStarred.append(" - " + lutemon.getDetails() + "\n");
            }
        }
        return sbStarred.toString();
    }
}
