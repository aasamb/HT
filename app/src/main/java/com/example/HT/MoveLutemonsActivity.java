package com.example.HT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;

public class MoveLutemonsActivity extends AppCompatActivity implements RecyclerViewCheckBoxListener, UpdateRecyclerViewInFragment {
    // Activity to move Lutemons into home, training of battlefield.
    // Real-time updating of the RecyclerView according to the actual movements of the Lutemons
    // does not work. Updating RecyclerViews requires restarting the activity.

    private Home home;
    private TrainingArea trainingArea;
    private BattleField battleField;
    private RadioGroup rgDestination;
    private HashMap<Integer, Lutemon> lutemonsToMove, lutemonsHashMap = new HashMap<>();
    private ViewPager2 viewPager2;
    private TabPagerAdapter tabPagerAdapter;
    private UpdateRecyclerViewInFragment updateRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_lutemons);
        lutemonsToMove = new HashMap<>();
        lutemonsHashMap = new HashMap<>();

        // Implementation of the interface for RecyclerView real-time update. Doesn't work.
        this.updateRecyclerView = (UpdateRecyclerViewInFragment) this;

        home = Home.getInstance();
        trainingArea = TrainingArea.getInstance();
        battleField = BattleField.getInstance();

        lutemonsHashMap.putAll(home.getLutemons());
        lutemonsHashMap.putAll(trainingArea.getLutemons());
        lutemonsHashMap.putAll(battleField.getLutemons());

        Button btnMoveLutemons = findViewById(R.id.btnMoveLutemons);
        rgDestination = findViewById(R.id.rgWhereTo);

        TabLayout tabLayout = findViewById(R.id.tabArea);
        this.viewPager2 = (ViewPager2) findViewById(R.id.fragmentArea);
        tabPagerAdapter = new TabPagerAdapter(this);
        viewPager2.setAdapter(tabPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
                //fragmentArea.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //fragmentArea.getAdapter().notifyDataSetChanged();
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
                //tabLayout.findViewById(R.id.rvLutemonsAtPlace).
                //tabPagerAdapter.notifyDataSetChanged();
            }
        });



        //getSupportFragmentManager().findFragmentById(R.id.fragmentArea);
        //destination = findViewById(R.id.moveLutemonsFrame).findViewById(R.id.rgWhere);
        //destination = moveToFragment.getActivity().findViewById(R.id.rgWhere);

        btnMoveLutemons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Storage destinationClass;
                String tagFrom = null;
                String tagTo;
                //LocationListFragment listFragment = (LocationListFragment) tabPagerAdapter.getItem(0);


                // If sentence to check if all the needed things are done before starting to move
                // any Lutemons.
                if ((rgDestination.getCheckedRadioButtonId() != -1) && (!lutemonsToMove.isEmpty()))   {
                    // Switch-case to determine the destination.
                    switch  (rgDestination.getCheckedRadioButtonId()) {
                        case (R.id.rbToHome):
                            destinationClass = home;
                            tagTo = "android:switcher:" + viewPager2.getId() + ":" + 0;
                            break;
                        case (R.id.rbToTraining):
                            destinationClass = trainingArea;
                            tagTo = "android:switcher:" + viewPager2.getId() + ":" + 1;
                            break;
                        case (R.id.rbToBattling):
                            destinationClass = battleField;
                            tagTo = "android:switcher:" + viewPager2.getId() + ":" + 2;
                            break;
                        default:
                            destinationClass = home;
                            tagTo = "android:switcher:" + viewPager2.getId() + ":" + 0;
                            break;
                    }

                    // Moving the selected Lutemons into the selected location.
                    for (int key : lutemonsToMove.keySet()) {
                        Location locationFrom = lutemonsHashMap.get(key).getLocation();
                        switch (locationFrom) {
                            case HOME:
                                tagFrom = "android:switcher:" + viewPager2.getId() + ":" + 0;
                                destinationClass.addLutemon(home.takeLutemonAway(key));
                                //getSupportFragmentManager().findFragmentById(R.id.fragmentArea).
                                //getSupportFragmentManager().findFragmentById(R.id.locationFrame).
                                //tabPagerAdapter.notifyLutemonsMoved(key, location, false);
                                //tabPagerAdapter.notifyLutemonsMoved(key, destinationClass.getLocation(), true);
                                break;
                            case TRAINING:
                                tagFrom = "android:switcher:" + viewPager2.getId() + ":" + 1;
                                destinationClass.addLutemon(trainingArea.takeLutemonAway(key));
                                System.out.println("MoveLutemonsActivity -> notifyLutemonsMoved: TRAINING");
                                //tabPagerAdapter.notifyLutemonsMoved(key, location, false);
                                //tabPagerAdapter.notifyLutemonsMoved(key, destinationClass.getLocation(), true);
                                break;
                            case BATTLE:
                                tagFrom = "android:switcher:" + viewPager2.getId() + ":" + 2;
                                destinationClass.addLutemon(battleField.takeLutemonAway(key));
                                System.out.println("MoveLutemonsActivity -> notifyLutemonsMoved: BATTLE");
                                //tabPagerAdapter.notifyLutemonsMoved(key, location, false);
                                //tabPagerAdapter.notifyLutemonsMoved(key, destinationClass.getLocation(), true);
                                break;
                            default:
                                tagFrom = "android:switcher:" + viewPager2.getId() + ":" + 0;
                                System.out.println("Virhe Lutemonin siirtämisessä");
                                break;
                        }
                    }

                    // Attempt to find the Fragments with tag and RecyclerViews and to call
                    // notifyDataSetChanged() methdod in the Adapter of the RecyclerView.
                    // Not working, commented out.
/*
                    System.out.println("MoveLutemonsActivity: tagFrom = " + tagFrom);
                    tagFrom = "move_fragment_tag";
                    LocationListFragment listFragmentFrom = (LocationListFragment) getSupportFragmentManager().findFragmentByTag(tagFrom);
                    RecyclerView recyclerViewFrom = listFragmentFrom.getView().findViewById(R.id.rvLutemonsAtPlace);
                    recyclerViewFrom.getAdapter().notifyDataSetChanged();

                    LocationListFragment listFragmentTo = (LocationListFragment) getSupportFragmentManager().findFragmentByTag(tagTo);
                    RecyclerView recyclerViewTo = listFragmentTo.getView().findViewById(R.id.rvLutemonsAtPlace);
                    recyclerViewTo.getAdapter().notifyDataSetChanged();

                    TabPagerAdapter adapter = (TabPagerAdapter) viewPager2.getAdapter();
                    adapter.notifyDataSetChanged();
*/
                    // Attempt to update RecyclerView real-time. Doesn't work.
                    MoveLutemonsActivity.this.updateRecyclerView.updateToFragment(LocationListFragment.TAG);
                }   else {
                    System.out.println("Mitään ei siirretty.");
                }
            }
        });
    }


    // Using RecyclerViewCheckBoxListener to update the ArrayList lutemonsToTrain
    // in real-time every time a check box is clicked.
    @Override
    public void onCheckboxStateChanged(int lutemonId, boolean isChecked) {
        if(isChecked)   {
            lutemonsToMove.put(lutemonId, lutemonsHashMap.get(lutemonId));
        }   else {
            lutemonsToMove.remove(lutemonId, lutemonsHashMap.get(lutemonId));
        }
        //lutemonsToMove.forEach((key, value) -> System.out.println(key + ": " + value.getName()));
    }

    // Override method regarding the attempt to update RecyclerView in real-time according to
    // changes in the actual locations of the Lutemons. Doesn't work.
    @Override
    public void updateToFragment(String fragmentTag) {
        this.tabPagerAdapter.updateToFragment(fragmentTag);
    }
}