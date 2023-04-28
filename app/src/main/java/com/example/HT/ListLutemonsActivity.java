package com.example.HT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ListLutemonsActivity extends AppCompatActivity {


    private Home home;
    private BattleField battleField;
    private TrainingArea trainingArea;
    private RecyclerView recyclerView;
    private LutemonListAdapter adapter;
    private RecyclerView.ViewHolder holder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lutemons);


        home = Home.getInstance();
        trainingArea = TrainingArea.getInstance();
        battleField = BattleField.getInstance();

        recyclerView = findViewById(R.id.rvLutemonsTraining);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LutemonListAdapter(getApplicationContext(), home.getLutemons(), trainingArea.getLutemons(), battleField.getLutemons());
        recyclerView.setAdapter(adapter);
/*
        System.out.println("lutemonsAtHome ListLutemonsActivityssa: (home = null: " + (home == null) + ")");
        home.getLutemons().forEach((key, value) -> System.out.println(key + ": " + value.getName()));
*/

    }
}