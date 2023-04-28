package com.example.HT;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class TrainingFragment extends Fragment {

    private TrainingArea trainingArea;

    private RecyclerView recyclerView;
    private LutemonFragmentListAdapter adapter;
    private ArrayList<Lutemon> lutemonsArrayList;


/*
    private RecyclerView recyclerView;
    private LutemonFragmentListAdapter adapter;
    private RecyclerView.ViewHolder holder;
*/

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
        View view = inflater.inflate(R.layout.fragment_training, container, false);

        trainingArea = TrainingArea.getInstance();

        recyclerView = view.findViewById(R.id.rvLutemonsTraining);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LutemonFragmentListAdapter(getContext().getApplicationContext(), trainingArea.getLutemons());
        recyclerView.setAdapter(adapter);

        return view;
    }
}