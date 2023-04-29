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
    private int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_lutemon);
        home = Home.getInstance();
        name = findViewById(R.id.etLutemonName);
        rgLutemonType = findViewById(R.id.rgLutemonType);
        rgImage = findViewById(R.id.rgImages);
    }



    public void addNewLutemon(View view){

        Lutemon lutemon = null;

        if (!name.getText().toString().isEmpty())   {

            switch (rgImage.getCheckedRadioButtonId())  {
                case (R.id.rbNoImg):
                    image = 0;
                    break;
                case (R.id.rbKirbyImg):
                    image = R.drawable.kirby;
                    break;
                case (R.id.rbLinkImg):
                    image = R.drawable.link;
                    break;
                case (R.id.rbLuigiImg):
                    image = R.drawable.luigi;
                    break;
                case (R.id.rbMarioImg):
                    image = R.drawable.mario;
                    break;
                default:
                    Toast.makeText(this, "Valitse kuva!", Toast.LENGTH_SHORT).show();
                    break;
            }

            if (/*(rgLutemonType.getCheckedRadioButtonId() != -1) &&*/ (rgImage.getCheckedRadioButtonId() != -1)) {
                switch (rgLutemonType.getCheckedRadioButtonId())   {
                    case (R.id.rbWhite):
                        lutemon = new White(name.getText().toString(), image);
                        break;
                    case (R.id.rbGreen):
                        lutemon = new Green(name.getText().toString(), image);
                        break;
                    case (R.id.rbPink):
                        lutemon = new Pink(name.getText().toString(), image);
                        break;
                    case (R.id.rbOrange):
                        lutemon = new Orange(name.getText().toString(), image);
                        break;
                    case (R.id.rbBlack):
                        lutemon = new Black(name.getText().toString(), image);
                        break;
                    default:
                        Toast.makeText(this, "Valitse tyyppi!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        } else {
            Toast.makeText(this, "Anna Lutemonille nimi!", Toast.LENGTH_SHORT).show();
            System.out.println("Ei nimeä.");
        }

        if (lutemon != null)    {
            home.createLutemon(lutemon);
            String print = "Activity: " + lutemon.getClass().getSimpleName() + "-Lutemon " + lutemon.getName() + " luotu!";
            System.out.println(print);
            Toast.makeText(this, print, Toast.LENGTH_SHORT).show();
        }   else {
            System.out.println("Lutemonia ei luotu.");
            Toast.makeText(this, "Lutemonia ei luotu.", Toast.LENGTH_SHORT).show();
        }

    }
}