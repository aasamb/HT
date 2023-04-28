package com.example.HT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;

public class MoveLutemonsActivity extends AppCompatActivity implements ToMoveCheckBoxListener {

    private Home home;
    private TrainingArea trainingArea;
    private BattleField battleField;
    private MoveToFragment moveToFragment;
    private RadioGroup destination;
    private Button btnMoveLutemons;
    private HashMap<Integer, Lutemon> lutemonsToMove, lutemonsHashMap = new HashMap<>();
    private LutemonFragmentListAdapter listAdapter;
    private TabPagerAdapter tabPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_lutemons);
        lutemonsToMove = new HashMap<>();
        lutemonsHashMap = new HashMap<>();

        home = Home.getInstance();
        trainingArea = TrainingArea.getInstance();
        battleField = BattleField.getInstance();

        lutemonsHashMap.putAll(home.getLutemons());
        lutemonsHashMap.putAll(trainingArea.getLutemons());
        lutemonsHashMap.putAll(battleField.getLutemons());

        btnMoveLutemons = findViewById(R.id.btnMoveLutemons);
        destination = findViewById(R.id.rgWhere);

        TabLayout tabLayout = findViewById(R.id.tabArea);
        ViewPager2 fragmentArea = findViewById(R.id.fragmentArea);
        tabPagerAdapter = new TabPagerAdapter(this);
        fragmentArea.setAdapter(tabPagerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fragmentArea.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        fragmentArea.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });



        //getSupportFragmentManager().findFragmentById(R.id.fragmentArea);
        moveToFragment = new MoveToFragment();
        //destination = findViewById(R.id.moveLutemonsFrame).findViewById(R.id.rgWhere);
        //destination = moveToFragment.getActivity().findViewById(R.id.rgWhere);

        btnMoveLutemons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Storage destinationClass;
                //System.out.println("Activityssä: onClickListener");
                System.out.println("Siirrettävät lutemonit:");
                lutemonsToMove.forEach((key, value) -> System.out.println(key + ": " + value.getName()));

                //System.out.println("RadioGroup destination: " + destination);

                switch  (destination.getCheckedRadioButtonId()) {
                    case (R.id.rbToHome):
                        destinationClass = home;
                        break;
                    case (R.id.rbToTraining):
                        destinationClass = trainingArea;
                        break;
                    case (R.id.rbToBattling):
                        destinationClass = battleField;
                        break;
                    default:
                        destinationClass = home;
                        break;
                }
                lutemonsToMove.forEach((key, value) ->  {
                    value.get
                });

            }
        });
/*
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.moveLutemonsFrame, moveToFragment)
                .commit();
*/
    }

    public LutemonFragmentListAdapter getListAdapter()  {
        System.out.println("tabPagerAdapter: " + tabPagerAdapter);
        tabPagerAdapter = new TabPagerAdapter(this);
        System.out.println("tabPagerAdapter: " + tabPagerAdapter);
        return tabPagerAdapter.getAdapter();
    }

    public void moveLutemons(@NonNull View view) {
        lutemonsToMove = tabPagerAdapter.getAdapter().getLutemonsToMove();

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

    @Override
    public void onCheckboxStateChanged(int position, boolean isChecked) {
        System.out.println("Activityssä: onCheckboxStateChanged; lutemonsToMove: " + lutemonsToMove);

        if(isChecked)   {
            lutemonsToMove.put(position, lutemonsHashMap.get(position));
        }   else {
            lutemonsToMove.remove(position, lutemonsHashMap.get(position));
        }
        lutemonsToMove.forEach((key, value) -> System.out.println(key + ": " + value.getName()));
        //MoveToFragment fragment = getSupportFragmentManager().findFragmentById(R.id.);

    }
}