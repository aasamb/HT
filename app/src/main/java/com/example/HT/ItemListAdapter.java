package com.example.HT;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemListAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private Context context;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    public ItemListAdapter(Context context, ArrayList<Lutemon> lutemons) {
        this.context = context;
        this.lutemons = lutemons;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.itemDetails.setText(lutemons.get(position).getDetails());
        holder.editImage.setImageResource(R.drawable.edit);
        holder.deleteImage.setImageResource(R.drawable.delete);
        holder.editDetails.setText(lutemons.get(position).getDetails());

        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                Storage.getInstance().removeItem(pos);
                notifyItemRemoved(pos);
            }
        });

        holder.editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();

                if(holder.editDetails.getVisibility() == View.VISIBLE)  {
                    Storage.getInstance().editItemDetails(pos, holder.editDetails.getText().toString());
                    holder.editDetails.setVisibility(View.GONE);
                    holder.itemDetails.setVisibility(View.VISIBLE);
                    notifyItemChanged(pos);
                }   else {
                    holder.itemDetails.setVisibility(View.GONE);
                    holder.editDetails.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }

}
