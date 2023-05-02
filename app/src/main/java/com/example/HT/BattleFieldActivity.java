package com.example.HT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class BattleFieldActivity extends AppCompatActivity implements RecyclerViewCheckBoxListener {

    RecyclerView rvLutemonsAtBattlefield, rvBattleText;
    ArrayList<String> battleText;
    HashMap<Integer, Lutemon> lutemonsHere, lutemonsToFight;
    BattleField battleField;
    private LutemonFragmentListAdapter adapter;
    private RecyclerViewCheckBoxListener listener;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_field);

        listener = (RecyclerViewCheckBoxListener) this;

        rvLutemonsAtBattlefield = findViewById(R.id.rvLutemonsAtBattlefield);
        rvBattleText = findViewById(R.id.rvBattleText);
        battleField = BattleField.getInstance();
        lutemonsHere = battleField.getLutemons();
        lutemonsToFight = new HashMap<>();

        rvLutemonsAtBattlefield.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LutemonFragmentListAdapter(getApplicationContext(), lutemonsHere);
        adapter.setCheckboxListener(new RecyclerViewCheckBoxListener() {
            @Override
            public void onCheckboxStateChanged(int position, boolean isChecked) {
                listener.onCheckboxStateChanged(position, isChecked);
            }
        });
        rvLutemonsAtBattlefield.setAdapter(adapter);

    }



    @Override
    public void onCheckboxStateChanged(int position, boolean isChecked) {
        if(isChecked)   {
            lutemonsToFight.put(position, lutemonsHere.get(position));
        }   else {
            lutemonsToFight.remove(position, lutemonsHere.get(position));
        }
        //System.out.println("BattleFieldActivity.onCheckboxStateChanged() lutemonsToMove: " + lutemonsToFight);
        //lutemonsToFight.forEach((key, value) -> System.out.println(key + ": " + value.getName()));
        //MoveToFragment fragment = getSupportFragmentManager().findFragmentById(R.id.);
    }


}