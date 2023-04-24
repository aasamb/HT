package com.example.HT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.HT.fragments.StarredFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private Storage storage;
    private ImageView ivSortAlphabet, ivSortRecent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storage = Storage.getInstance();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.starredFrame, new StarredFragment())
                .commit();

        TabLayout tabLayout = findViewById(R.id.tabArea);
        ViewPager2 fragmentArea = findViewById(R.id.fragmentArea);
        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(this);
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
    }

    public void switchToAddNewLutemon(View view)  {
        Intent intent = new Intent(this, AddNewLutemonActivity.class);
        startActivity(intent);
    }

    public void switchToListLutemons(View view)  {
        Intent intent = new Intent(this, ListLutemonsActivity.class);
        startActivity(intent);
    }

    public void switchToMoveLutemons(View view)  {
        Intent intent = new Intent(this, MoveLutemonsActivity.class);
        startActivity(intent);
    }

    public void switchToBattleFieldActivity(View view)  {
        Intent intent = new Intent(this, BattleFieldActivity.class);
        startActivity(intent);
    }

    public void switchTo(View view)  {
        Intent intent = new Intent(this, TrainLutemonsActivity.class);
        startActivity(intent);
    }


/*
    public void sortAlphabet(View view) {
        storage.sortAlphabet();
        adapter.notifyDataSetChanged();
    }

    public void sortRecent(View view)   {
        storage.sortRecent();
        adapter.notifyDataSetChanged();
    }
*/
}