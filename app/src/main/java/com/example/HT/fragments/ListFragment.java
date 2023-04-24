package com.example.HT.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.HT.ItemListAdapter;
import com.example.HT.R;
import com.example.HT.Storage;


public class ListFragment extends Fragment {

    private Storage storage;

    private RecyclerView recyclerView;
    private ItemListAdapter adapter;


/*
    private RecyclerView recyclerView;
    private ItemListAdapter adapter;
    private RecyclerView.ViewHolder holder;
*/

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
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        storage = Storage.getInstance();

        recyclerView = view.findViewById(R.id.rvItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ItemListAdapter(getContext().getApplicationContext(), storage.getItems());
        recyclerView.setAdapter(adapter);





        return view;
    }
}