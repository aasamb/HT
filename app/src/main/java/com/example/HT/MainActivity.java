package com.example.HT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Storage storage;
    private ImageView ivSortAlphabet, ivSortRecent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void switchToAddNewLutemon(View view)  {
        Intent intent = new Intent(this, AddNewLutemonActivity.class);
        startActivity(intent);
    }

    public void switchToListLutemons(View view)  {
        Intent intent = new Intent(this, ListLutemonsActivity.class);
        startActivity(intent);
    }

    public void switchToMoveLutemons(View view)  {
        Intent intent = new Intent(this, MoveLutemonsActivity.class);
        startActivity(intent);
    }

    public void switchToBattleFieldActivity(View view)  {
        Intent intent = new Intent(this, BattleFieldActivity.class);
        startActivity(intent);
    }

    public void switchToTrainLutemonsActivity(View view)  {
        Intent intent = new Intent(this, TrainLutemonsActivity.class);
        startActivity(intent);
    }


/*
    public void sortAlphabet(View view) {
        storage.sortAlphabet();
        adapter.notifyDataSetChanged();
    }

    public void sortRecent(View view)   {
        storage.sortRecent();
        adapter.notifyDataSetChanged();
    }
*/
}