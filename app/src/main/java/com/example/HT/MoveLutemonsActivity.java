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

    private Home home;
    private TrainingArea trainingArea;
    private BattleField battleField;
    private MoveToFragment moveToFragment;
    private RadioGroup rgDestination;
    private Button btnMoveLutemons;
    private HashMap<Integer, Lutemon> lutemonsToMove, lutemonsHashMap = new HashMap<>();
    private LutemonFragmentListAdapter listAdapter;
    private ViewPager2 viewPager2;
    private TabPagerAdapter tabPagerAdapter;
    private UpdateRecyclerViewInFragment updateRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_lutemons);
        lutemonsToMove = new HashMap<>();
        lutemonsHashMap = new HashMap<>();

        this.updateRecyclerView = (UpdateRecyclerViewInFragment) this;

        home = Home.getInstance();
        trainingArea = TrainingArea.getInstance();
        battleField = BattleField.getInstance();

        lutemonsHashMap.putAll(home.getLutemons());
        lutemonsHashMap.putAll(trainingArea.getLutemons());
        lutemonsHashMap.putAll(battleField.getLutemons());

        btnMoveLutemons = findViewById(R.id.btnMoveLutemons);
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
                //System.out.println("onTabSelected");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //fragmentArea.getAdapter().notifyDataSetChanged();
                //System.out.println("onTabReselected");
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

        //getSupportFragmentManager().tag;
        btnMoveLutemons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Storage destinationClass;
                //System.out.println("Activityssä: onClickListener");
                //System.out.println("Siirrettävät lutemonit:");
                //lutemonsToMove.forEach((key, value) -> System.out.println(key + ": " + value.getName()));


                String tagFrom = null; // = "android:switcher:" + fragmentArea.getId() + ":" + 0;
                String tagTo;  // = "android:switcher:" + fragmentArea.getId() + ":" + 0;
                //LocationListFragment listFragment = (LocationListFragment) tabPagerAdapter.getItem(0);

                //System.out.println("RadioGroup destination: " + destination);

                if ((rgDestination.getCheckedRadioButtonId() != -1) && (!lutemonsToMove.isEmpty()))   {
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

                    for (int key : lutemonsToMove.keySet()) {
/*
                    Integer key = entry.getKey();
                    Lutemon value = entry.getValue();
*/
                        Location locationFrom = lutemonsHashMap.get(key).getLocation();
                        switch (locationFrom) {
                            case HOME:
                                tagFrom = "android:switcher:" + viewPager2.getId() + ":" + 0;
                                destinationClass.addLutemon(home.takeLutemonAway(key));
                                System.out.println("MoveLutemonsActivity -> notifyLutemonsMoved: HOME");
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
/*
                    if (lutemonsHashMap.containsKey(key))   {
                    }
*/
                    }

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
                    MoveLutemonsActivity.this.updateRecyclerView.updateToFragment(LocationListFragment.TAG);
                }   else {
                    System.out.println("Mitään ei siirretty.");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (viewPager2.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
        }

    }

/*
    public LutemonFragmentListAdapter getListAdapter()  {
        System.out.println("tabPagerAdapter: " + tabPagerAdapter);
        tabPagerAdapter = new TabPagerAdapter(this);
        System.out.println("tabPagerAdapter: " + tabPagerAdapter);
        return tabPagerAdapter.getAdapter();
    }
*/

/*
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
*/

    @Override
    public void onCheckboxStateChanged(int lutemonId, boolean isChecked) {
        if(isChecked)   {
            lutemonsToMove.put(lutemonId, lutemonsHashMap.get(lutemonId));
        }   else {
            lutemonsToMove.remove(lutemonId, lutemonsHashMap.get(lutemonId));
        }
        System.out.println("MoveLutemonsActivity.onCheckboxStateChanged() lutemonsToMove: " + lutemonsToMove);
        //lutemonsToMove.forEach((key, value) -> System.out.println(key + ": " + value.getName()));
        //MoveToFragment fragment = getSupportFragmentManager().findFragmentById(R.id.);
    }

    @Override
    public void updateToFragment(String fragmentTag) {
        this.tabPagerAdapter.updateToFragment(fragmentTag);
    }
}