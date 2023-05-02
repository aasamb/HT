package com.example.HT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class TrainLutemonsActivity extends AppCompatActivity implements RecyclerViewCheckBoxListener {

    private RecyclerView rvLutemonsAtTraining;
    private TextView tvTrainingResults;
    private ArrayList<String> trainingResults;
    private HashMap<Integer, Lutemon> lutemonsHere, lutemonsToTrain = new HashMap<>();
    private TrainingArea trainingArea;
    private LutemonFragmentListAdapter adapter;
    private RecyclerViewCheckBoxListener listener;
    private Button btnTrainSelected;
    private RadioGroup rgFeatureToTrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_lutemons);

        listener = (RecyclerViewCheckBoxListener) this;

        btnTrainSelected = findViewById(R.id.btnTrain);
        rvLutemonsAtTraining = findViewById(R.id.rvLutemonsAtTraining);
        tvTrainingResults = findViewById(R.id.tvTrainingResults);
        rgFeatureToTrain = findViewById(R.id.rgFeatureToTrain);

        trainingArea = TrainingArea.getInstance();
        lutemonsHere = trainingArea.getLutemons();

        rvLutemonsAtTraining.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LutemonFragmentListAdapter(getApplicationContext(), lutemonsHere);

        // Using RecyclerViewCheckBoxListener to update the ArrayList lutemonsToTrain
        // in real-time every time a check box is clicked.
        adapter.setCheckboxListener(new RecyclerViewCheckBoxListener() {
            @Override
            public void onCheckboxStateChanged(int position, boolean isChecked) {
                listener.onCheckboxStateChanged(position, isChecked);
            }
        });
        rvLutemonsAtTraining.setAdapter(adapter);

        // Training functionality with an OnClickListener.
        btnTrainSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Doing the things only if there's stuff in the list of Lutemons to train.
                if (lutemonsToTrain.size() > 0) {
                    switch (rgFeatureToTrain.getCheckedRadioButtonId()) {
                        case (R.id.rbTrainAttack):
                            trainingResults = trainingArea.train(lutemonsToTrain, true);
                            makeTrainingResultsPrint();
                            break;
                        case (R.id.rbTrainDefense):
                            trainingResults = trainingArea.train(lutemonsToTrain, false);
                            makeTrainingResultsPrint();
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), "Valitse harjoitettava ominaisuus!", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }   else {
                    Toast.makeText(getApplicationContext(), "Ei valittuja Lutemoneja!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onCheckboxStateChanged(int position, boolean isChecked) {
        if(isChecked)   {
            lutemonsToTrain.put(position, lutemonsHere.get(position));
        }   else {
            lutemonsToTrain.remove(position, lutemonsHere.get(position));
        }
    }

    // Externalizing functionality to update the TextView of action results into
    // separate method.
    private void makeTrainingResultsPrint()  {
        // Checking if the list is not empty, or even exists to avoid NullPointerExceptions.
        if (trainingResults != null && !trainingResults.isEmpty())  {
            // Initializing the TextView.
            tvTrainingResults.setText("");
            trainingResults.forEach(s -> {
                tvTrainingResults.append(s + "\n");
            });
        }
    }
}