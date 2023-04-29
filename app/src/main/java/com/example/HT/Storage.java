package com.example.HT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public abstract class Storage {
    protected String name;
    protected HashMap<Integer, Lutemon> lutemons = new HashMap<>();
    protected Location location;

    public void listLutemons() {
        lutemons.forEach((id, lutemon) -> System.out.println(id + ": " + lutemon.getName()));
    }

    public void addLutemon(Lutemon lutemon) {
        //System.out.println("addLutemon-metodi, luokassa " + this.getLocation().toString() + ", siirrettävä lutemon: " + lutemon.getId() + ": from " + lutemon.getLocation().toString());
        lutemons.put(lutemon.getId(), lutemon);
        lutemon.setLocation(this.location);
        //System.out.println(lutemon.getId() + " @" + lutemon.getLocation().toString());
        //System.out.println("HashMap lutemons @" + this.getLocation().toString() + ": ");
        //lutemons.forEach((key, value) -> System.out.println(key + ": " + value.getName()));

    }

    public String getName() {
        return name;
    }

    public HashMap<Integer, Lutemon> getLutemons() {
        return lutemons;
    }

    public Lutemon getLutemon(int id) {
        return lutemons.get(id);
    }

    public Lutemon takeLutemonAway(int id)  {
        return lutemons.remove(id);
    }

    public Location getLocation() {
        return location;
    }
}
