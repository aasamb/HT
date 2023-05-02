package com.example.HT;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class LutemonListAdapter extends RecyclerView.Adapter<LutemonViewHolder> {
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
        //System.out.println("lutemonsAtTraining null: " + String.valueOf(lutemonsAtTraining == null));
        //System.out.println("lutemonsAtBattleField null: " + String.valueOf(lutemonsAtBattleField == null));
        this.lutemonsMap.putAll(this.lutemonsAtHome);
        this.lutemonsMap.putAll(this.lutemonsAtTraining);
        this.lutemonsMap.putAll(this.lutemonsAtBattleField);
        this.lutemons.addAll(lutemonsMap.values());


/*
        System.out.println("lutemonsMap ItemListAdapterissa: ");
        lutemonsMap.forEach((key, value) -> System.out.println(key + ": " + value.getName()));
        System.out.println("lutemonsAtHome ItemListAdapterissa: ");
        lutemonsAtHome.forEach((key, value) -> System.out.println(key + ": " + value.getName()));
        System.out.println("lutemons ItemListAdapterissa: ");
        lutemons.forEach(lutemon -> System.out.println(lutemon.getName()));
        System.out.println("kotona: " + lutemonsAtHome.size() + ", treenaamassa: " + lutemonsAtTraining.size() + ", taistelussa: " + lutemonsAtBattleField.size());
        System.out.println("Yhteislistan koko: " + lutemons.size());
*/
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
/*
        holder.attackTitle.setText("Hyökkäys: ");
        holder.defenseTitle.setText("Puolustus: ");
        holder.healthTitle.setText("Elämä: ");
        holder.expTitle.setText("Kokemus: ");
*/
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

/*
        if(lutemonsAtTraining.containsValue(lutemons.get(position)))    {
            locationString = "Treenaamassa";
        } else if (lutemonsAtHome.containsValue(lutemons.get(position))) {
            locationString = "Kotona";
        } else if (lutemonsAtBattleField.containsValue(lutemons.get(position))) {
            locationString = "Taistelemassa";
        }   else {
            locationString = "Ei tietoa";
        }
*/
        holder.location.setText(locationString);


    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }

}
