package com.example.HT;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddNewLutemonActivity extends AppCompatActivity {

    private Home home;
    private EditText name;
    private RadioGroup rgLutemonType, rgImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_lutemon);
        home = Home.getInstance();
        name = findViewById(R.id.etLutemonName);
        rgLutemonType = findViewById(R.id.rgLutemonType);
    }


    public void addNewLutemon(View view) {

        int image;
        Lutemon lutemon = null;

        // Check if there's a name given.
        if (!name.getText().toString().isEmpty()) {
            switch (rgLutemonType.getCheckedRadioButtonId()) {
                case (R.id.rbGreen):
                    image = R.drawable.luigi;
                    lutemon = new Green(name.getText().toString(), image);
                    break;
                case (R.id.rbPink):
                    image = R.drawable.kirby;
                    lutemon = new Pink(name.getText().toString(), image);
                    break;
                case (R.id.rbBlue):
                    image = R.drawable.link;
                    lutemon = new Blue(name.getText().toString(), image);
                    break;
                case (R.id.rbRed):
                    image = R.drawable.mario;
                    lutemon = new Red(name.getText().toString(), image);
                    break;
                default:
                    // If not type chosen. Or something unexpected.
                    Toast.makeText(this, "Valitse Lutemonin tyyppi!", Toast.LENGTH_SHORT).show();
                    break;
            }

        } else {
            // Reminding user. No Lutemon creation.
            Toast.makeText(this, "Anna Lutemonille nimi!", Toast.LENGTH_SHORT).show();
            //System.out.println("Ei nimeä.");
        }

        // If there's a Lutemon created.
        if (lutemon != null) {
            home.createLutemon(lutemon);
            String print = "Activity: " + lutemon.getClass().getSimpleName() + "-Lutemon " + lutemon.getName() + " luotu!";
            //System.out.println(print);
            Toast.makeText(this, print, Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise only reminding user.
            //System.out.println("Lutemonia ei luotu.");
            Toast.makeText(this, "Lutemonia ei luotu.", Toast.LENGTH_SHORT).show();
        }

    }
}