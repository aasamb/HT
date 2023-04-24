package com.example.HT.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.HT.Lutemon;
import com.example.HT.R;
import com.example.HT.Storage;

public class AddFragment extends Fragment {

    EditText itemDetails;
    Switch isStarred;
    Storage storage;
    Button btnAddItem;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);


        itemDetails = view.findViewById(R.id.etInsertDetails);
        isStarred = view.findViewById(R.id.swStarred);
        storage = Storage.getInstance();

        btnAddItem = (Button) view.findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String details = itemDetails.getText().toString();

                if (!details.equals(""))    {
                    storage.addItem(new Lutemon(details, isStarred.isChecked()));
                }   else {
                    Toast.makeText(getActivity(), "Lisää teksti", Toast.LENGTH_LONG).show();
                }


            }
        });

        return view;
    }





}