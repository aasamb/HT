package com.example.HT;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class BattleFragment extends Fragment {

    private BattleField battleField;

    private RecyclerView recyclerView;
    private LutemonFragmentListAdapter adapter;
    private ArrayList<Lutemon> lutemonsArrayList;

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
        View view = inflater.inflate(R.layout.fragment_battle, container, false);

        battleField = BattleField.getInstance();

        recyclerView = view.findViewById(R.id.rvLutemonsBattling);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LutemonFragmentListAdapter(getContext().getApplicationContext(), battleField.getLutemons());
        recyclerView.setAdapter(adapter);

        return view;
    }





}