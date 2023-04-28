package com.example.HT;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabPagerAdapter extends FragmentStateAdapter {

    private Home home = Home.getInstance();
    private TrainingArea trainingArea = TrainingArea.getInstance();
    private BattleField battleField = BattleField.getInstance();
    private LocationListFragment fragment;


    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)   {
            case 0:
                fragment = new LocationListFragment(home.getLutemons());
                break;
            case 1:
                fragment = new LocationListFragment(trainingArea.getLutemons());
                break;
            case 2:
                fragment = new LocationListFragment(battleField.getLutemons());
                break;
            default:
                fragment = new LocationListFragment(home.getLutemons());
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public LutemonFragmentListAdapter getAdapter()  {
        System.out.println("getAdapter(): fragment == null: " + (fragment == null));
        fragment = new LocationListFragment();
        System.out.println("getAdapter(): fragment == null: " + (fragment == null));
        return fragment.getAdapter();
    }
}
