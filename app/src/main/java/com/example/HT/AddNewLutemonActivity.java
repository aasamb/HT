package com.example.HT;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewLutemonActivity extends AppCompatActivity {

    private Home home;
    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_lutemon);
        home = Home.getInstance();
        name = findViewById(R.id.etLutemonName);
    }



    public void addNewLutemon(View view){
        home.createLutemon(new Black(name.getText().toString()));
        Toast.makeText(this, String.valueOf(Lutemon.getNumberOfCreatedLutemons()), Toast.LENGTH_LONG).show();
    }
}