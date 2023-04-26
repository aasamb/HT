package com.example.HT;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemListAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private Context context;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    private HashMap<Integer, Lutemon> lutemonsAtHome, lutemonsAtTraining, lutemonsAtBattleField, lutemonsMap = new HashMap<>();
    private String locationString;

    public ItemListAdapter(Context context, HashMap<Integer, Lutemon> lutemonsAtHome, HashMap<Integer, Lutemon> lutemonsAtTraining, HashMap<Integer, Lutemon> lutemonsAtBattleField) {
        this.context = context;
        this.lutemonsAtHome = lutemonsAtHome;
        this.lutemonsAtBattleField = lutemonsAtBattleField;
        this.lutemonsAtTraining = lutemonsAtTraining;
        System.out.println("lutemonsMap null: " + String.valueOf(lutemonsMap == null));
        System.out.println("lutemonsAtHome null: " + String.valueOf(lutemonsAtHome == null));
        System.out.println("lutemonsAtTraining null: " + String.valueOf(lutemonsAtTraining == null));
        System.out.println("lutemonsAtBattleField null: " + String.valueOf(lutemonsAtBattleField == null));
        this.lutemonsMap.putAll(this.lutemonsAtHome);
        this.lutemonsMap.putAll(this.lutemonsAtTraining);
        this.lutemonsMap.putAll(this.lutemonsAtBattleField);
        this.lutemons.addAll(lutemonsMap.values());
        System.out.println("Erillisten HashMappien koko: " + lutemonsAtBattleField.size() + lutemonsAtTraining.size() + lutemonsAtTraining.size());
        System.out.println("Yhteislistan koko: " + lutemons.size());
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.lutemon_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.name.setText(lutemons.get(position).getName());
        holder.type.setText("(" + lutemons.get(position).getType() + ")");
        holder.attackTitle.setText("Hyökkäys: ");
        holder.defenseTitle.setText("Puolustus: ");
        holder.healthTitle.setText("Elämä: ");
        holder.expTitle.setText("Kokemus: ");
        holder.attackValue.setText(lutemons.get(position).getAttack());
        holder.defenseValue.setText(lutemons.get(position).getDefense());
        holder.healthValue.setText(lutemons.get(position).getHealth() + "/" + lutemons.get(position).getMaxHealth());
        holder.expValue.setText(lutemons.get(position).getExperience());
        holder.id.setText(String.valueOf(lutemons.get(position).getId()));

        if(lutemonsAtTraining.containsValue(lutemons.get(position)))    {
            locationString = "Treenaamassa";
        } else if (lutemonsAtHome.containsValue(lutemons.get(position))) {
            locationString = "Kotona";
        } else if (lutemonsAtBattleField.containsValue(lutemons.get(position))) {
            locationString = "Taistelemassa";
        }   else {
            locationString = "Ei tietoa";
        }
        holder.location.setText(locationString);


    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }

}
