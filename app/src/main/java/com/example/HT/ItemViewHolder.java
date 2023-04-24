package com.example.HT;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.HT.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView itemDetails;
    EditText editDetails;
    ImageView deleteImage, editImage;


    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        itemDetails = itemView.findViewById(R.id.tvItemDetails);
        editDetails = itemView.findViewById(R.id.etEditDetails);
        deleteImage = itemView.findViewById(R.id.ivDeleteItem);
        editImage = itemView.findViewById(R.id.ivEditItem);
    }
}
