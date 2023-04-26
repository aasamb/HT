package com.example.HT;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView name, type, attackTitle, attackValue, defenseTitle, defenseValue, healthTitle, healthValue, expTitle, expValue, location;
    ImageView lutemonImg;


    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tvLutemonName);
        type = itemView.findViewById(R.id.tvLutemonType);
        attackTitle = itemView.findViewById(R.id.tvAttackTitle);
        attackValue = itemView.findViewById(R.id.tvAttack);
        defenseTitle = itemView.findViewById(R.id.tvDefenseTitle);
        defenseValue = itemView.findViewById(R.id.tvDefense);
        healthTitle = itemView.findViewById(R.id.tvHealthTitle);
        healthValue = itemView.findViewById(R.id.tvHealth);
        expTitle = itemView.findViewById(R.id.tvExpTitle);
        expValue = itemView.findViewById(R.id.tvExp);
        location = itemView.findViewById(R.id.tvLocation);
        lutemonImg = itemView.findViewById(R.id.ivLutemonImg);
    }
}
