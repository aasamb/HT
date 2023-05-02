package com.example.HT;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class LutemonListAdapter extends RecyclerView.Adapter<LutemonViewHolder> {
    // Adapter for ListLutemonsActivity to create RecyclerView to show all Lutemons.

    private Context context;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    private HashMap<Integer, Lutemon> lutemonsAtHome = new HashMap<>(), lutemonsAtTraining = new HashMap<>(), lutemonsAtBattleField = new HashMap<>(), lutemonsMap = new HashMap<>();
    private String locationString;
    private Location location;

    public LutemonListAdapter(Context context, HashMap<Integer, Lutemon> lutemonsAtHome, HashMap<Integer, Lutemon> lutemonsAtTraining, HashMap<Integer, Lutemon> lutemonsAtBattleField) {
        this.context = context;
        this.lutemonsAtHome = lutemonsAtHome;
        this.lutemonsAtBattleField = lutemonsAtBattleField;
        this.lutemonsAtTraining = lutemonsAtTraining;
        // Converting three HashMaps of Lutemons in three different locations into one HashMap
        this.lutemonsMap.putAll(this.lutemonsAtHome);
        this.lutemonsMap.putAll(this.lutemonsAtTraining);
        this.lutemonsMap.putAll(this.lutemonsAtBattleField);
        // Converting the big HashMap into ArrayList. This is done in this order to get the
        // Lutemons in the ArrayList show in the order of IDs/creation.
        this.lutemons.addAll(lutemonsMap.values());
    }

    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonViewHolder(LayoutInflater.from(context).inflate(R.layout.lutemon_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        holder.name.setText(lutemons.get(position).getName());
        holder.type.setText("(" + lutemons.get(position).getType() + ")");
        holder.attackValue.setText(String.valueOf(lutemons.get(position).getAttack()));
        holder.wins.setText(String.valueOf(lutemons.get(position).getWins()));
        holder.defenseValue.setText(String.valueOf(lutemons.get(position).getDefense()));
        holder.defeats.setText(String.valueOf(lutemons.get(position).getDefeats()));
        holder.healthValue.setText(lutemons.get(position).getHealth() + "/" + lutemons.get(position).getMaxHealth());
        holder.trainingDays.setText(lutemons.get(position).getTrainingDays());
        holder.expValue.setText(String.valueOf(lutemons.get(position).getExperience()));
        holder.id.setText("ID: " + (lutemons.get(position).getId()));
        holder.lutemonImg.setImageResource(lutemons.get(position).getImage());
        location = lutemons.get(position).getLocation();
        // Making the String to put into location TextView according to the actual
        // location variable of the Lutemon in case.
        switch (location)   {
            case HOME:
                locationString = "Kotona";
                break;
            case TRAINING:
                locationString = "Treenaamassa";
                break;
            case BATTLE:
                locationString = "Taistelemassa";
                break;
            default:
                locationString = "Ei tietoa";
                break;

        }
        holder.location.setText(locationString);
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }

}
