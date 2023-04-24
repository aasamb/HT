package com.example.HT.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.HT.R;
import com.example.HT.Storage;

public class StarredFragment extends Fragment {

    Storage storage;
    TextView starredItems;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        storage = Storage.getInstance();

        View view = inflater.inflate(R.layout.fragment_starred, container, false);

        starredItems = view.findViewById(R.id.tvStarredItems);

        starredItems.setText(storage.getStarredString());

        return view;
    }

    public void updateStarred() {
        starredItems.setText(storage.getStarredString());
    }
}