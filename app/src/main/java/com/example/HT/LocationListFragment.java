package com.example.HT;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LocationListFragment extends Fragment {
    public static final String TAG = "move_list_fragment";

    //private Home home;
    private RecyclerView recyclerView;
    private LutemonFragmentListAdapter adapter;
    //private ArrayList<Lutemon> lutemonsArrayList;
    private HashMap<Integer, Lutemon> lutemonsAtPlace;
    private RecyclerViewCheckBoxListener listener;
    private static Parcelable recyclerViewState;

    public LocationListFragment(HashMap<Integer, Lutemon> lutemonsAtPlace)  {
        this.lutemonsAtPlace = lutemonsAtPlace;
    }

/*
    public LocationListFragment()   {
    }
*/

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (RecyclerViewCheckBoxListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement RecyclerViewCheckBoxListener");
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
        recyclerView = view.findViewById(R.id.rvLutemonsAtPlace);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LutemonFragmentListAdapter(getContext().getApplicationContext(), lutemonsAtPlace);
        recyclerView.setAdapter(adapter);
        adapter.setCheckboxListener(new RecyclerViewCheckBoxListener() {
            @Override
            public void onCheckboxStateChanged(int position, boolean isChecked) {
                listener.onCheckboxStateChanged(position, isChecked);
            }
        });
        //System.out.println("adapter: " + adapter);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        //System.out.println("LocationListFragment " + ((!lutemonsAtPlace.isEmpty()) ? new ArrayList<Lutemon>(lutemonsAtPlace.values()).get(0).getLocation() : null) + ": onPause()");
/*
        int position = 0;
        for (Map.Entry<Integer, Lutemon> entry : lutemonsAtPlace.entrySet()) {
            System.out.println(new ArrayList<Lutemon>(lutemonsAtPlace.values()).get(position).getId());
            adapter.notifyItemRemoved(position);
            position++;
        }
*/
        //recyclerViewState = Objects.requireNonNull(recyclerView.getLayoutManager()).onSaveInstanceState();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        //Objects.requireNonNull(recyclerView.getLayoutManager()).onRestoreInstanceState(recyclerViewState);
/*
        System.out.println("LocationListFragment " + ((!lutemonsAtPlace.isEmpty()) ? new ArrayList<>(lutemonsAtPlace.values()).get(0).getLocation() : null) + ": onResume()");
        int position = 0;
        for (Map.Entry<Integer, Lutemon> entry : lutemonsAtPlace.entrySet()) {
            System.out.println(new ArrayList<>(lutemonsAtPlace.values()).get(position).getId());
            adapter.notifyItemInserted((lutemonsAtPlace.size() - (position + 1)));
            position++;
        }
*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //recyclerViewState = null;
        //System.out.println("LocationListFragment " + ((!lutemonsAtPlace.isEmpty()) ? new ArrayList<Lutemon>(lutemonsAtPlace.values()).get(0).getLocation() : null) + ": onDestroy()");
    }

    public void notifyLutemonsMoved(int id, boolean added)  {
        System.out.println("LocationListFragment.notifyLutemonsMoved()");

        int position = 0;

        System.out.println("adapter: " + adapter);

        for (Lutemon lutemon: adapter.getLutemonsHere()) {
            System.out.println("Lutemonin id: " + lutemon.getId() + ", annettu id: " + id + ", positio: " + position);
            if (lutemon.getId() < id) {
                position++;
            }
            if (lutemon.getId() == id)  {
                System.out.println(lutemon.getId() + ": " + lutemon.getName() + " löytyy listalta!");
                break;
            }
        }
/*
        adapter.getLutemonsHere().forEach(lutemon    -> {
        });
*/
        if (added)  {
            System.out.println("Adapteria yritetään huomauttaa positionin: " + position + " lisäämisestä.");
            adapter.notifyItemInserted(position);
        }   else {
            System.out.println("Adapteria yritetään huomauttaa positionin: " + position + " poistamisesta.");
            adapter.notifyItemRemoved(position);
        }
    }

    public void receiveUpdate() {
        //System.out.println("LocationListFragment.receiveUpdate(), adapter: " + adapter);
        if (adapter != null)    {
            //System.out.println("notifyDataSetChanged()");
            adapter.notifyDataSetChanged();
        }
    }

/*
    public LutemonFragmentListAdapter getAdapter()  {
        return (LutemonFragmentListAdapter) recyclerView.getAdapter();
    }
*/
}