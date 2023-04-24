package com.example.HT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public abstract class Storage {
    protected String name;
    protected HashMap<Integer, Lutemon> lutemons = new HashMap<>();

    public void listLutemons() {
        lutemons.forEach((id, lutemon) -> System.out.println(id + ": " + lutemon.getName()));
    }

    public void addLutemon(Lutemon lutemon) {
        lutemons.put(lutemon.getId(), lutemon);
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


}
