package com.example.HT;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView lutemonName, lutemonType, attackTitle, attackValue, defenseTitle, defenseValue, healthTitle, healthValue, expTitle, expValue;
    ImageView lutemonImage;


    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        lutemonName = itemView.findViewById(R.id.tvLutemonName);
        lutemonType = itemView.findViewById(R.id.tvLutemonType);
        attackTitle = itemView.findViewById(R.id.tvAttackTitle);
        attackValue = itemView.findViewById(R.id.tvAttack);
        defenseTitle = itemView.findViewById(R.id.tvDefenseTitle);
        defenseValue = itemView.findViewById(R.id.tvDefense);
        healthTitle = itemView.findViewById(R.id.tvHealthTitle);
        healthValue = itemView.findViewById(R.id.tvHealth);
        expTitle = itemView.findViewById(R.id.tvExpTitle);
        expValue = itemView.findViewById(R.id.tvExp);
    }
}
