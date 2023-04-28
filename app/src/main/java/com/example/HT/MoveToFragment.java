package com.example.HT;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import java.util.HashMap;

public class MoveToFragment extends Fragment {

    private Home home;
    private BattleField battleField;
    private TrainingArea trainingArea;
    private RadioGroup destination;
    private Button btnMoveLutemons;
    private LutemonFragmentViewHolder viewHolder;
    private HashMap<Integer, Lutemon> lutemonsToMove;
    private LutemonFragmentListAdapter adapter;

    public MoveToFragment(LutemonFragmentListAdapter adapter)   {
        this.adapter = adapter;
    }

    public MoveToFragment() {

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

        //storage = Storage.getInstance();

        View view = inflater.inflate(R.layout.fragment_move_to, container, false);

        destination = view.findViewById(R.id.rgWhere);
        btnMoveLutemons = view.findViewById(R.id.btnMoveLutemons);
/*
        btnMoveLutemons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lutemonsToMove = new MoveLutemonsActivity().getListAdapter().getLutemonsToMove();

                System.out.println("Siirrettävät lutemonit:");
                lutemonsToMove.forEach((key, value) -> System.out.println(key + ": " + value.getName()));

                System.out.println("RadioGroup destination: " + destination);
                switch  (destination.getCheckedRadioButtonId()) {
                    case (R.id.rbToHome):
                        break;
                    case (R.id.rbToTraining):
                        break;
                    case (R.id.rbToBattling):
                        break;
                    default:
                        break;
                }
            }
        });
*/

        return view;
    }

/*
    public void moveLutemons(@NonNull View view) {
        destination = view.findViewById(R.id.rgWhere);

        lutemonsToMove = adapter.getLutemonsToMove();

        System.out.println("Siirrettävät lutemonit:");
        lutemonsToMove.forEach((key, value) -> System.out.println(key + ": " + value.getName()));

        switch(destination.getCheckedRadioButtonId()) {
            case (R.id.rbToHome):
                break;
            case (R.id.rbToTraining):
                break;
            case (R.id.rbToBattling):
                break;
        }
    }
*/

    public void moveLutemons(View view) {
        System.out.println("moveLutemons-metodi MoveToFragmentissa.");
    }
}