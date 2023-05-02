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
    private Context context;
    private ArrayList<Lutemon> lutemonsHere = new ArrayList<>();
    private HashMap<Integer, Lutemon> lutemonsHereMap, lutemonsToMove = new HashMap<>();
    private RecyclerViewCheckBoxListener listener;


    public LutemonFragmentListAdapter(Context context, HashMap<Integer, Lutemon> lutemonsHereMap) {
        this.context = context;
        this.lutemonsHereMap = lutemonsHereMap;
        this.lutemonsHere.addAll(this.lutemonsHereMap.values());
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        //listener = (RecyclerViewCheckBoxListener) context;
    }

    @NonNull
    @Override
    public LutemonFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonFragmentViewHolder(LayoutInflater.from(context).inflate(R.layout.lutemon_to_check_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonFragmentViewHolder holder, int position) {

        holder.moveThis.setText("ID " + lutemonsHere.get(position).getId() + ":  " + lutemonsHere.get(position).getName() + " (" + lutemonsHere.get(position).getClass().getSimpleName() + ")");

        holder.moveThis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                //System.out.println("position: " + position + "; pos = getAdapterPosition(): " + pos);
                listener.onCheckboxStateChanged(lutemonsHere.get(pos).getId(), holder.moveThis.isChecked());
                //System.out.println("Klikattu!");
                if(holder.moveThis.isChecked())    {
                    lutemonsToMove.put(lutemonsHere.get(pos).getId(), lutemonsHere.get(pos));
                    //lutemonsToMove.forEach((key, value) -> System.out.println(key + ": " + value.getName()));
                }   else {
                    lutemonsToMove.remove(lutemonsHere.get(pos).getId(), lutemonsHere.get(pos));
                    //lutemonsToMove.forEach((key, value) -> System.out.println(key + ": " + value.getName()));
                }
            }
        });
    }

/*
    @Override
    public void onViewAttachedToWindow(@NonNull LutemonFragmentViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        //notifyDataSetChanged();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull LutemonFragmentViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        System.out.println("RecyclerView " + ((lutemonsHere.size() != 0) ? lutemonsHere.get(0).getLocation() : null) + ": onViewDetachedFromWindow()");
        //notifyDataSetChanged();
    }
*/

    /*
    @Override
    public void onCheckboxStateChanged(int position, boolean isChecked) {

    }
*/

    public ArrayList<Lutemon> getLutemonsHere() {
        return lutemonsHere;
    }

    public void update(HashMap<Integer, Lutemon> newLutemons)   {
        this.lutemonsHere = new ArrayList<>(newLutemons.values());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return lutemonsHere.size();
    }

/*
    public HashMap<Integer, Lutemon> getLutemonsToMove()    {
        return lutemonsToMove;
    }
*/

    public void setCheckboxListener(RecyclerViewCheckBoxListener listener) {
        this.listener = listener;
    }

}
