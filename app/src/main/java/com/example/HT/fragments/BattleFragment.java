package com.example.HT.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.HT.BattleField;
import com.example.HT.Home;
import com.example.HT.ItemListAdapter;
import com.example.HT.Lutemon;
import com.example.HT.R;
import com.example.HT.Storage;
import com.example.HT.TrainingArea;

import java.util.ArrayList;

public class BattleFragment extends Fragment {

    private Storage home, trainingArea, battleField;

    private RecyclerView recyclerView;
    private ItemListAdapter adapter;
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
        View view = inflater.inflate(R.layout.fragment_training, container, false);

        home = Home.getInstance();
        trainingArea = TrainingArea.getInstance();
        battleField = BattleField.getInstance();

        recyclerView = view.findViewById(R.id.rvAllLutemons);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ItemListAdapter(getContext().getApplicationContext(), storage.getItems());
        recyclerView.setAdapter(adapter);





        return view;
    }





}