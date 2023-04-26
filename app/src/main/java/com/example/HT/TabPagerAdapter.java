package com.example.HT;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.HT.fragments.BattleFragment;
import com.example.HT.fragments.TrainingFragment;

public class TabPagerAdapter extends FragmentStateAdapter {


    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)   {
            case 0:
                return new TrainingFragment();
            case 1:
                return new BattleFragment();
            default:
                return new TrainingFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
