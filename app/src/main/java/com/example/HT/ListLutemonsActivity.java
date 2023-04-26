package com.example.HT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ListLutemonsActivity extends AppCompatActivity {


    private Storage home, battleField, trainingArea;
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ItemListAdapter(getApplicationContext(), storage.getItems());
        recyclerView.setAdapter(adapter);
    }
}