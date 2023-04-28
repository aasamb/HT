package com.example.HT;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class LutemonFragmentListAdapter extends RecyclerView.Adapter<LutemonFragmentViewHolder> implements ToMoveCheckBoxListener {
    private Context context;
    private ArrayList<Lutemon> lutemonsHere = new ArrayList<>();
    private HashMap<Integer, Lutemon> lutemonsHereMap, lutemonsToMove = new HashMap<>();
    private ToMoveCheckBoxListener listener;


    public LutemonFragmentListAdapter(Context context, HashMap<Integer, Lutemon> lutemonsHereMap) {
        this.context = context;
        this.lutemonsHereMap = lutemonsHereMap;
        this.lutemonsHere.addAll(this.lutemonsHereMap.values());
    }

    public void setCheckBoxListener(ToMoveCheckBoxListener listener)    {
        this.listener = listener;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        //listener = (ToMoveCheckBoxListener) context;
    }

    @NonNull
    @Override
    public LutemonFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonFragmentViewHolder(LayoutInflater.from(context).inflate(R.layout.lutemons_here_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonFragmentViewHolder holder, int position) {

        holder.moveThis.setText("ID " + lutemonsHere.get(position).getId() + ":  " + lutemonsHere.get(position).getName() + " (" + lutemonsHere.get(position).getClass().getSimpleName() + ")");

        holder.moveThis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                System.out.println("position: " + position + "; pos = getAdapterPosition(): " + pos);
                listener.onCheckboxStateChanged(pos, holder.moveThis.isChecked());
                System.out.println("Klikattu!");
                if(holder.moveThis.isChecked())    {
                    lutemonsToMove.put(lutemonsHere.get(pos).getId(), lutemonsHere.get(pos));
                    lutemonsToMove.forEach((key, value) -> System.out.println(key + ": " + value.getName()));
                }   else {
                    lutemonsToMove.remove(lutemonsHere.get(pos).getId(), lutemonsHere.get(pos));
                    lutemonsToMove.forEach((key, value) -> System.out.println(key + ": " + value.getName()));
                }
            }
        });
    }

    @Override
    public void onCheckboxStateChanged(int position, boolean isChecked) {

    }

    @Override
    public int getItemCount() {
        return lutemonsHere.size();
    }

    public HashMap<Integer, Lutemon> getLutemonsToMove()    {
        return lutemonsToMove;
    }

    public void setCheckboxListener(ToMoveCheckBoxListener listener) {
        this.listener = listener;
    }

}
