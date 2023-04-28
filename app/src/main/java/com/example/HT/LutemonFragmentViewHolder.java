package com.example.HT;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.HT.R;

public class LutemonFragmentViewHolder extends RecyclerView.ViewHolder {

    CheckBox moveThis;

    public LutemonFragmentViewHolder(@NonNull View itemView) {
        super(itemView);
        moveThis = itemView.findViewById(R.id.cbMoveThis);
    }
}
