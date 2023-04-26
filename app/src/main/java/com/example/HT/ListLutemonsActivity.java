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
    private ItemListAdapter adapter;
    private RecyclerView.ViewHolder holder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lutemons);


        home = Home.getInstance();
        trainingArea = TrainingArea.getInstance();
        battleField = BattleField.getInstance();

        recyclerView = findViewById(R.id.rvAllLutemons);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemListAdapter(getApplicationContext(), home.getLutemons(), trainingArea.getLutemons(), battleField.getLutemons());
        recyclerView.setAdapter(adapter);
    }
}