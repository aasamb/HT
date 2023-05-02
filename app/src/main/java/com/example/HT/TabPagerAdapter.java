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
    private HashMap<Integer, Lutemon> lutemonHashMap;
    private int position;
    private LocationListFragment homeFragment, trainingFragment, battlefieldFragment, listFragment;
    //private LocationListFragment fragment;

    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        //fragment = new LocationListFragment();
        homeFragment = new LocationListFragment(home.getLutemons());
        trainingFragment = new LocationListFragment(trainingArea.getLutemons());
        battlefieldFragment = new LocationListFragment(battleField.getLutemons());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        this.position = position;
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
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        */
/*
        System.out.println("TabPagerAdapter: onAttachedToRecyclerView()");
        switch (position)   {
            case 0:
                lutemonHashMap = home.getLutemons();
                System.out.println("TabPagerAdapter " + ((lutemonHashMap.size() != 0) ? lutemonHashMap.get(0).getLocation() : null) + ": onAttachedToRecyclerView()");
                break;
            case 1:
                lutemonHashMap = trainingArea.getLutemons();
                System.out.println("TabPagerAdapter " + ((lutemonHashMap.size() != 0) ? lutemonHashMap.get(0).getLocation() : null) + ": onAttachedToRecyclerView()");
                break;
            case 2:
                lutemonHashMap = battleField.getLutemons();
                System.out.println("TabPagerAdapter " + ((lutemonHashMap.size() != 0) ? lutemonHashMap.get(0).getLocation() : null) + ": onAttachedToRecyclerView()");
                break;
            default:
                lutemonHashMap = home.getLutemons();
                System.out.println("TabPagerAdapter " + ((lutemonHashMap.size() != 0) ? lutemonHashMap.get(0).getLocation() : null) + ": onAttachedToRecyclerView()");
                break;
        }
*//*

    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }
*/

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

    @Override
    public void updateToFragment(String fragmentTag) {
        if (this.listFragment != null && fragmentTag.equals(LocationListFragment.TAG)) {
            this.listFragment.receiveUpdate();
        }

    }

/*
    public LutemonFragmentListAdapter getAdapter()  {
*/
/*
        System.out.println("getAdapter(): fragment == null: " + (fragment == null));
        fragment = new LocationListFragment();
*//*

        System.out.println("getAdapter(): fragment == null: " + (fragment == null));
        return fragment.getAdapter();
    }
*/
}
