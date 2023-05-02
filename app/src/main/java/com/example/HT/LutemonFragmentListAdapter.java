package com.example.HT;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class LutemonFragmentListAdapter extends RecyclerView.Adapter<LutemonFragmentViewHolder> {
    // Adapter to use a RecyclerView in several different occasions. Provides a RecyclerView
    // of Lutemons of a given HashMap with only id, name and type of Lutemons visible
    // with a checkbox.

    private Context context;
    private ArrayList<Lutemon> lutemonsHere = new ArrayList<>();
    private HashMap<Integer, Lutemon> lutemonsToMove = new HashMap<>();
    private RecyclerViewCheckBoxListener listener;


    public LutemonFragmentListAdapter(Context context, HashMap<Integer, Lutemon> lutemonsHereMap) {
        this.context = context;
        this.lutemonsHere.addAll(lutemonsHereMap.values());
    }

    @NonNull
    @Override
    public LutemonFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonFragmentViewHolder(LayoutInflater.from(context).inflate(R.layout.lutemon_to_check_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonFragmentViewHolder holder, int position) {

        holder.moveThis.setText("ID " + lutemonsHere.get(position).getId() + ":  " + lutemonsHere.get(position).getName() + " (" + lutemonsHere.get(position).getClass().getSimpleName() + ")");


        // Using OnClickListener in the checkboxes of the RecyclerView to update
        // the data structure of Lutemons to move into different location in real-time.
        holder.moveThis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                listener.onCheckboxStateChanged(lutemonsHere.get(pos).getId(), holder.moveThis.isChecked());
                if(holder.moveThis.isChecked())    {
                    lutemonsToMove.put(lutemonsHere.get(pos).getId(), lutemonsHere.get(pos));
                }   else {
                    lutemonsToMove.remove(lutemonsHere.get(pos).getId(), lutemonsHere.get(pos));
                }
            }
        });
    }


    public ArrayList<Lutemon> getLutemonsHere() {
        return lutemonsHere;
    }

    @Override
    public int getItemCount() {
        return lutemonsHere.size();
    }


    public void setCheckboxListener(RecyclerViewCheckBoxListener listener) {
        this.listener = listener;
    }

}
