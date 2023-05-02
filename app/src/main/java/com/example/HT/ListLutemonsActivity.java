package com.example.HT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.HashMap;

public class ListLutemonsActivity extends AppCompatActivity {
    // Activity to list all Lutemons. Only static RecyclerView, no functionality.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lutemons);


        Home home = Home.getInstance();
        TrainingArea trainingArea = TrainingArea.getInstance();
        BattleField battleField = BattleField.getInstance();

        RecyclerView recyclerView = findViewById(R.id.rvAllLutemons);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LutemonListAdapter adapter = new LutemonListAdapter(getApplicationContext(), home.getLutemons(), trainingArea.getLutemons(), battleField.getLutemons());
        recyclerView.setAdapter(adapter);
    }
}