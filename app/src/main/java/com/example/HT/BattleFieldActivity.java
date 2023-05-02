package com.example.HT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class BattleFieldActivity extends AppCompatActivity implements RecyclerViewCheckBoxListener {

    private RecyclerView rvLutemonsAtBattlefield, rvBattleText;
    private TextView tvBattlePrint;
    private ArrayList<String> battleText;
    private HashMap<Integer, Lutemon> lutemonsHere, lutemonsToFight;
    private BattleField battleField;
    private LutemonFragmentListAdapter adapter;
    private RecyclerViewCheckBoxListener listener;
    private Button btnStartFight;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_field);

        listener = (RecyclerViewCheckBoxListener) this;

        btnStartFight = findViewById(R.id.btnFight);
        rvLutemonsAtBattlefield = findViewById(R.id.rvLutemonsAtBattlefield);
        //rvBattleText = findViewById(R.id.rvBattleText);
        tvBattlePrint = findViewById(R.id.tvBattlePrint);
        battleField = BattleField.getInstance();
        lutemonsHere = battleField.getLutemons();
        lutemonsToFight = new HashMap<>();

        rvLutemonsAtBattlefield.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LutemonFragmentListAdapter(getApplicationContext(), lutemonsHere);

        // Using RecyclerViewCheckBoxListener to update the ArrayList lutemonsToTrain
        // in real-time every time a check box is clicked.
        adapter.setCheckboxListener(new RecyclerViewCheckBoxListener() {
            @Override
            public void onCheckboxStateChanged(int position, boolean isChecked) {
                listener.onCheckboxStateChanged(position, isChecked);
            }
        });

        rvLutemonsAtBattlefield.setAdapter(adapter);


        btnStartFight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                battleText = new ArrayList<>();
                // Check if there's at least and only two Lutemons selected.
                if (lutemonsToFight.size() == 2)    {
                    double starterFactor = Math.random();
                    ArrayList<Lutemon> lutemonsToFightArrayList = new ArrayList<>(lutemonsToFight.values());

                    Lutemon lutemon1;
                    Lutemon lutemon2;

                    // Let's randomize a bit which of the Lutemons starts the fight.
                    if (starterFactor > 0.5)   {
                        lutemon1 = lutemonsToFightArrayList.get(0);
                        lutemon2 = lutemonsToFightArrayList.get(1);
                    }   else {  // The other way around.
                        lutemon1 = lutemonsToFightArrayList.get(1);
                        lutemon2 = lutemonsToFightArrayList.get(0);
                    }

                    //ArrayList<Lutemon> lutemonsHereBeforeFight = new ArrayList<>(lutemonsHere.values());
                    battleText = battleField.fight(lutemon1, lutemon2);
                    makeBattlePrint();
                    lutemonsToFight = new HashMap<>();
                    //((LutemonFragmentListAdapter) rvLutemonsAtBattlefield.getAdapter()).notifyDataSetChanged();
/*
                    lutemonsHereBeforeFight.forEach(lutemon -> {
                        System.out.println("Tarkastetaan ID: " + lutemon.getId() + ", health: " + lutemon.getHealthString());
                        if (lutemon.getLocation() != Location.BATTLE)    {
                            System.out.println("huomautetaan Lutemonin ID: " + lutemon.getId() + ", pos: " + lutemonsHereBeforeFight.indexOf(lutemon) + " poistamisesta");
                            //((LutemonFragmentListAdapter) rvLutemonsAtBattlefield.getAdapter()).notifyItemRemoved(lutemonsHereBeforeFight.indexOf(lutemon));
                        }
                    });
*/
                    //rvLutemonsAtBattlefield.getAdapter().notifyDataSetChanged();
                }   else {
                    System.out.println("Valitse 2 Lutemonia.");
                    Toast.makeText(getApplicationContext(), "Valitse 2 Lutemonia.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume()");
        makeBattlePrint();
    }

    @Override
    public void onCheckboxStateChanged(int position, boolean isChecked) {
        if(isChecked)   {
            lutemonsToFight.put(position, lutemonsHere.get(position));
        }   else {
            lutemonsToFight.remove(position, lutemonsHere.get(position));
        }
    }


    // Externalizing functionality to update the TextView of action results into
    // separate method.
    private void makeBattlePrint()  {
        // Checking if the list is not empty, or even exists to avoid NullPointerExceptions.
        if (battleText != null && !battleText.isEmpty())  {
            // Initializing the TextView.
            tvBattlePrint.setText("");
            battleText.forEach(s -> {
                tvBattlePrint.append(s + "\n");
            });
        }
    }
}