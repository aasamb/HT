package com.example.HT;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.HashMap;

public class TabPagerAdapter extends FragmentStateAdapter implements UpdateRecyclerViewInFragment {

    private final Home home = Home.getInstance();
    private final TrainingArea trainingArea = TrainingArea.getInstance();
    private final BattleField battleField = BattleField.getInstance();
    private LocationListFragment homeFragment, trainingFragment, battlefieldFragment, listFragment;

    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        homeFragment = new LocationListFragment(home.getLutemons());
        trainingFragment = new LocationListFragment(trainingArea.getLutemons());
        battlefieldFragment = new LocationListFragment(battleField.getLutemons());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)   {
            case 0:
                listFragment = new LocationListFragment(home.getLutemons());
                break;
            case 1:
                listFragment = new LocationListFragment(trainingArea.getLutemons());
                break;
            case 2:
                listFragment = new LocationListFragment(battleField.getLutemons());
                break;
            default:
                listFragment = new LocationListFragment(home.getLutemons());
                break;
        }
        return  listFragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

/*
    public void notifyLutemonsMoved(int id, Location location, boolean added)  {
        System.out.println("TabPagerAdapter.notifyLutemonsMoved()");

        switch (location)   {
            case HOME:
                if (homeFragment != null)   {
                    homeFragment.notifyLutemonsMoved(id, added);
                }   else {
                    System.out.println("homeFragment: " + homeFragment);
                }
                break;
            case BATTLE:
                if (battlefieldFragment != null)   {
                    battlefieldFragment.notifyLutemonsMoved(id, added);
                }   else {
                    System.out.println("battlefieldFragment: " + battlefieldFragment);
                }
                break;
            case TRAINING:
                if (trainingFragment != null)   {
                    trainingFragment.notifyLutemonsMoved(id, added);
                }   else {
                    System.out.println("trainingFragment: " + trainingFragment);
                }
                break;
            default:
                System.out.println("Sijainnin kanssa epäselvyyksiä");
                break;
        }
    }
*/

    @Override
    public void updateToFragment(String fragmentTag) {
        if (this.listFragment != null && fragmentTag.equals(LocationListFragment.TAG)) {
            this.listFragment.receiveUpdate();
        }
    }
}
