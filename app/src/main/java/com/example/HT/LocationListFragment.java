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
    // Fragment to present a list of Lutemons at a specific location,
    // to be checked to move to another location.
    // One fragment to be used in three different locations, location specification is done by
    // passing the correct HashMap to the Fragment.

    public static final String TAG = "move_list_fragment";

    private LutemonFragmentListAdapter adapter;
    //private ArrayList<Lutemon> lutemonsArrayList;
    private HashMap<Integer, Lutemon> lutemonsAtPlace;
    private RecyclerViewCheckBoxListener listener;
    private static Parcelable recyclerViewState;

    // The list of Lutemons in this particular location is given to the Fragment when created.
    public LocationListFragment(HashMap<Integer, Lutemon> lutemonsAtPlace)  {
        this.lutemonsAtPlace = lutemonsAtPlace;
    }


    // Using RecyclerViewCheckBoxListener to update the ArrayList lutemonsToTrain
    // in real-time every time a check box is clicked.
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
        //private Home home;
        RecyclerView recyclerView = view.findViewById(R.id.rvLutemonsAtPlace);
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
    public void onResume() {
        super.onResume();
        // Trying to update RecyclerView without opening the whole activity again. Doesn't work.
        adapter.notifyDataSetChanged();
        //Objects.requireNonNull(recyclerView.getLayoutManager()).onRestoreInstanceState(recyclerViewState);
/*
        // Another attempt to update RecyclerView. Not working.
        System.out.println("LocationListFragment " + ((!lutemonsAtPlace.isEmpty()) ? new ArrayList<>(lutemonsAtPlace.values()).get(0).getLocation() : null) + ": onResume()");
        int position = 0;
        for (Map.Entry<Integer, Lutemon> entry : lutemonsAtPlace.entrySet()) {
            System.out.println(new ArrayList<>(lutemonsAtPlace.values()).get(position).getId());
            adapter.notifyItemInserted((lutemonsAtPlace.size() - (position + 1)));
            position++;
        }
*/
    }

    // Another attempt to update RecyclerView when clikcing the button to move Lutemons. Not working.
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
        if (added)  {
            System.out.println("Adapteria yritetään huomauttaa positionin: " + position + " lisäämisestä.");
            adapter.notifyItemInserted(position);
        }   else {
            System.out.println("Adapteria yritetään huomauttaa positionin: " + position + " poistamisesta.");
            adapter.notifyItemRemoved(position);
        }
    }

    // Method implementation regarding UpdateRecyclerViewInFragment interface.
    // Not working due to adapter being =null most of the times.
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