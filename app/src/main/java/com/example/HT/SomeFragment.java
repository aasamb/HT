package com.example.HT;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.HT.LutemonListAdapter;
import com.example.HT.Lutemon;
import com.example.HT.R;
import com.example.HT.Storage;

import java.util.ArrayList;


public class SomeFragment extends Fragment {

    private Storage home, trainingArea, battleField;

    private RecyclerView recyclerView;
    private LutemonListAdapter adapter;
    private ArrayList<Lutemon> lutemonsArrayList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_some, container, false);

        return view;
    }
}