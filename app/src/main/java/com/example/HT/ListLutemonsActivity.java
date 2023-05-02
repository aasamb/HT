package com.example.HT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.HashMap;

public class ListLutemonsActivity extends AppCompatActivity {


    private Home home;
    private BattleField battleField;
    private TrainingArea trainingArea;
    private RecyclerView recyclerView;
    private LutemonListAdapter adapter;
    private RecyclerView.ViewHolder holder;
    private HashMap<Integer, Lutemon> lutemonsAtHome, lutemonsAtTraining, lutemonsAtBattleField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lutemons);


        home = Home.getInstance();
        trainingArea = TrainingArea.getInstance();
        battleField = BattleField.getInstance();

        lutemonsAtHome = home.getLutemons();
        lutemonsAtTraining = trainingArea.getLutemons();
        lutemonsAtBattleField = battleField.getLutemons();

        System.out.println("Sijainnit  ListLutemonsActivityssa: home=null: " + (home == null) + ", trainingArea=null: " + (trainingArea == null) + ", battleField=null: " + (battleField == null));
        System.out.print("Kotona: ");
        home.getLutemons().forEach((key, value) -> System.out.println(key + ": " + value.getName()));
        System.out.print("Treenaamassa: ");
        trainingArea.getLutemons().forEach((key, value) -> System.out.println(key + ": " + value.getName()));
        System.out.print("Tappelussa: ");
        battleField.getLutemons().forEach((key, value) -> System.out.println(key + ": " + value.getName()));


        recyclerView = findViewById(R.id.rvAllLutemons);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LutemonListAdapter(getApplicationContext(), home.getLutemons(), trainingArea.getLutemons(), battleField.getLutemons());
        recyclerView.setAdapter(adapter);


    }
}