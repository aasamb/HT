package com.example.HT;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

public class LocationListFragment extends Fragment {

    private Home home;
    private RecyclerView recyclerView;
    private LutemonFragmentListAdapter adapter;
    private ArrayList<Lutemon> lutemonsArrayList;
    private HashMap<Integer, Lutemon> lutemonsAtPlace;
    private ToMoveCheckBoxListener listener;

    public LocationListFragment(HashMap<Integer, Lutemon> lutemonsAtPlace)  {
        this.lutemonsAtPlace = lutemonsAtPlace;
    }

    public LocationListFragment()   {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (ToMoveCheckBoxListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement ToMoveCheckBoxListener");
        }

    }

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
        View view = inflater.inflate(R.layout.fragment_location, container, false);

        home = Home.getInstance();

        recyclerView = view.findViewById(R.id.rvLutemonsAtPlace);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LutemonFragmentListAdapter(getContext().getApplicationContext(), lutemonsAtPlace);
        recyclerView.setAdapter(adapter);

        adapter.setCheckboxListener(new ToMoveCheckBoxListener() {
            @Override
            public void onCheckboxStateChanged(int position, boolean isChecked) {
                listener.onCheckboxStateChanged(position, isChecked);
            }
        });

        return view;
    }

    public LutemonFragmentListAdapter getAdapter()  {
        return (LutemonFragmentListAdapter) recyclerView.getAdapter();
    }
}